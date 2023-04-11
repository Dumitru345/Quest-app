package questapp.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import questapp.entity.Status;
import questapp.entity.Quest;
import questapp.entity.User;
import questapp.repository.QuestRepository;
import questapp.repository.UserRepository;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private QuestRepository questRepo;

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());

		return "signup_form";
	}

	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		userRepo.save(user);

		return "register_success";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);

		return "users";
	}

	@GetMapping("/quests")
	public String questsList(Model model) {
		List<Quest> quests = questRepo.findAll();
		model.addAttribute("questsList", quests);

		return "quests";
	}

	@PostMapping("/quests")
	public String addQuest(@RequestParam String questBody, Model model, Principal principal) {
		User user = userRepo.findByEmail(principal.getName()); // Retrieve the logged-in user
		Quest quest = new Quest();
		quest.setUser(user);
		quest.setQuestBody(questBody);
		quest.setStatus(Status.OPEN);
		quest.setDoneBy(new ArrayList<>()); // initialize the list of users who marked the quest as done to an empty list
		questRepo.save(quest);
		model.addAttribute("quests", questRepo.findAll());
		return "redirect:/quests";
	}

	@PostMapping("/quests/done/{id}")
	public String markAsDone(@PathVariable("id") Long id, Principal principal) {
		Quest quest = questRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid quest Id:" + id));
		User doneBy = userRepo.findByEmail(principal.getName());

		// Check if the user who is trying to mark the quest as done is not the same user who created the quest
		if (quest.getUser().equals(doneBy)) {
			throw new IllegalArgumentException("You cannot mark your own quest as done!");
		}

		// Check if the quest has already been marked as done by the user
		if (quest.getDoneBy().contains(doneBy)) {
			throw new IllegalArgumentException("You have already marked this quest as done!");
		}

		List<User> doneByList = quest.getDoneBy();
		doneByList.add(doneBy);
		quest.setStatus(Status.DONE);
		questRepo.save(quest);
		doneBy.addTokensForQuestDone();
		doneBy.setExp(doneBy.getExp() + 10);
		doneBy.updateExpAndTitle();
		userRepo.save(doneBy);
		return "redirect:/quests";
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String handleIllegalArgumentException(Model model, IllegalArgumentException ex) {
		model.addAttribute("errorMessage", ex.getMessage());
		return "error";
	}



}
