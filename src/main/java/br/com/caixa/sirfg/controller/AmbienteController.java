package br.com.caixa.sirfg.controller;

import br.com.caixa.sirfg.model.Ambiente;
import br.com.caixa.sirfg.service.AmbienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

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

    @RequestMapping("/ambiente/findById")
    @ResponseBody
    public Optional<Ambiente> findById(Long id) {
        return ambienteService.findById(id);
    }

    @PostMapping(value = "/ambiente/atualizar")
    public String update(Ambiente ambiente, RedirectAttributes redirectAttributes) {
        ambienteService.update(ambiente);

        redirectAttributes.addFlashAttribute("success", true);
        redirectAttributes.addFlashAttribute("message", "Informações dos ambientes atualizados com sucesso!");

        return "redirect:/listaAmbiente";
    }
}