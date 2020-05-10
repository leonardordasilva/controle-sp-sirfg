package br.com.caixa.sirfg.controller;

import br.com.caixa.sirfg.model.Sp;
import br.com.caixa.sirfg.service.SpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/controleSP")
public class SpController {
	
	private final SpService spService;

	public SpController(SpService spService) {
		this.spService = spService;
	}

	@PostMapping("/adicionar")
	public String create(Sp sp, RedirectAttributes redirectAttributes) {
		spService.create(sp);
		redirectAttributes.addFlashAttribute("message", "SP cadastrada com sucesso!");
		return "redirect:/controleSP";
	}

	@RequestMapping("")
	public String findAll(Model model) {
		List<Sp> sps = spService.findAll();
		model.addAttribute("sps", sps);

		return "sp/listaCompleta";
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	public Optional<Sp> findById(Long Id) {
		return spService.findById(Id);
	}

	@PutMapping
	@RequestMapping(value="/atualizar")
	public String update(Sp sp, RedirectAttributes redirectAttributes) {
		spService.update(sp);
		redirectAttributes.addFlashAttribute("message", "SP atualizada com sucesso!");
		return "redirect:/controleSP";
	}

	@DeleteMapping
	@RequestMapping(value="/excluir")
	public String delete(Sp sp, RedirectAttributes redirectAttributes) {
		spService.delete(sp.getId());
		redirectAttributes.addFlashAttribute("message", "SP excluída com sucesso!");
		return "redirect:/controleSP";
	}

	@DeleteMapping
	@RequestMapping(value="/deleteAll")
	public String deleteAll(Sp sp, RedirectAttributes redirectAttributes) {
		spService.deleteAll();
		redirectAttributes.addFlashAttribute("message", "SPs excluídas com sucesso!");
		return "redirect:/controleSP";
	}
}