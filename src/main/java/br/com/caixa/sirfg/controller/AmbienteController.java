package br.com.caixa.sirfg.controller;

import br.com.caixa.sirfg.model.Ambiente;
import br.com.caixa.sirfg.service.AmbienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Optional<Ambiente> findById(Long Id) {
        return ambienteService.findById(Id);
    }

    /*@PutMapping
    @RequestMapping(value = "/atualizar")
    public String update(Sp sp, RedirectAttributes redirectAttributes) {
        String nomeObejto = sp.getNome();
        List<Sp> spList = ambienteService.findAllByNome(nomeObejto);

        for (Sp sp1 : spList) {
            if (sp1.getId() != sp.getId()
                    && sp1.getNome().equalsIgnoreCase(sp.getNome())
                    && sp1.getTipoObjeto() == sp.getTipoObjeto()) {
                redirectAttributes.addFlashAttribute("success", false);
                redirectAttributes.addFlashAttribute("message", "Já existe um objeto do tipo " + sp.getTipoObjeto().getDescricaoObjeto() + " com o nome " + sp.getNome() + " cadastrado!");
                return "redirect:/" + LISTA_SP;
            }
        }

        ambienteService.update(sp);
        redirectAttributes.addFlashAttribute("success", true);
        redirectAttributes.addFlashAttribute("message", "Objeto " + sp.getNome() + " atualizado com sucesso!");
        return "redirect:/" + LISTA_SP;
    }*/
}