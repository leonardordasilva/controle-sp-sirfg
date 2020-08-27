package br.com.caixa.sirfg.controller;

import br.com.caixa.sirfg.model.Ambiente;
import br.com.caixa.sirfg.service.AmbienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AmbienteController {
    private final AmbienteService ambienteService;

    public AmbienteController(AmbienteService ambienteService) {
        this.ambienteService = ambienteService;
    }

    @RequestMapping("/listaAmbiente")
    public String findAll(Model model) {
        model.addAttribute("ambienteList", ambienteService.findAll());

        return "ambiente/listaCompleta";
    }

    @PostMapping("/ambiente/adicionar")
    public String create(Ambiente ambiente, RedirectAttributes redirectAttributes) {

        ambienteService.create(ambiente);

        redirectAttributes.addFlashAttribute("success", true);
        redirectAttributes.addFlashAttribute("message", "Informações dos ambientes cadastrado com sucesso!");

        return "redirect:/listaAmbiente";
    }

    @PostMapping(value = "/ambiente/atualizar")
    public String update(Ambiente ambiente, RedirectAttributes redirectAttributes) {
        ambienteService.update(ambiente);

        redirectAttributes.addFlashAttribute("success", true);
        redirectAttributes.addFlashAttribute("message", "Informações dos ambientes atualizados com sucesso!");

        return "redirect:/listaAmbiente";
    }
}