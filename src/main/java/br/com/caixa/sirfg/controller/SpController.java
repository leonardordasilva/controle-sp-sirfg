package br.com.caixa.sirfg.controller;

import br.com.caixa.sirfg.model.AmbienteEnum;
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
public class SpController {
    public static final String LISTA_SP = "listaSp";
    public static final String LISTA_DIFERENCAS = "listaDiferencas";
    public static final String TITULO_DES_TQS = "Lista de SPs diferentes em DES e TQS";
    public static final String TITULO_TQS_HMP = "Lista de SPs diferentes em TQS e HMP";
    public static final String TITULO_HMP_PRD = "Lista de SPs diferentes em HMP e PRD";

    private final SpService spService;

    public SpController(SpService spService) {
        this.spService = spService;
    }

    @PostMapping("/adicionar")
    public String create(Sp sp, RedirectAttributes redirectAttributes) {
        spService.create(sp);
        redirectAttributes.addFlashAttribute("message", "SP cadastrada com sucesso!");
        return "redirect:/" + LISTA_SP;
    }

    @RequestMapping("/listaSp")
    public String findAll(Model model) {
        List<Sp> sps = spService.findAll();
        model.addAttribute("sps", sps);

        return "sp/listaCompleta";
    }

    @RequestMapping("/listaDesTqs")
    public String findAllDesTqs(Model model) {
        List<Sp> sps = spService.findAllDesTqs();

        model.addAttribute("sps", sps);
        model.addAttribute("titulo", TITULO_DES_TQS);
        model.addAttribute("ambiente", "desTqs");
        model.addAttribute("actionEqualizarAll", "/equalizarAll/desTqs");
        model.addAttribute("actionEqualizar", "/equalizar/desTqs");
        model.addAttribute("coluna1", "Data versão DES");
        model.addAttribute("coluna2", "Data versão TQS");
        model.addAttribute("valorColuna1", AmbienteEnum.DES);
        model.addAttribute("valorColuna2", AmbienteEnum.TQS);

        return "sp/" + LISTA_DIFERENCAS;
    }

    @RequestMapping("/listaTqsHmp")
    public String findAllTqsHmp(Model model) {
        List<Sp> sps = spService.findAllTqsHmp();

        model.addAttribute("sps", sps);
        model.addAttribute("titulo", TITULO_TQS_HMP);
        model.addAttribute("ambiente", "tqsHmp");
        model.addAttribute("actionEqualizarAll", "/equalizarAll/tqsHmp");
        model.addAttribute("actionEqualizar", "/equalizar/tqsHmp");
        model.addAttribute("coluna1", "Data versão TQS");
        model.addAttribute("coluna2", "Data versão HMP");
        model.addAttribute("valorColuna1", AmbienteEnum.TQS);
        model.addAttribute("valorColuna2", AmbienteEnum.HMP);

        return "sp/" + LISTA_DIFERENCAS;
    }

    @RequestMapping("/listaHmpPrd")
    public String findAllHmpPrd(Model model) {
        List<Sp> sps = spService.findAllHmpPrd();

        model.addAttribute("sps", sps);
        model.addAttribute("titulo", TITULO_HMP_PRD);
        model.addAttribute("ambiente", "hmpPrd");
        model.addAttribute("actionEqualizarAll", "/equalizarAll/hmpPrd");
        model.addAttribute("actionEqualizar", "/equalizar/hmpPrd");
        model.addAttribute("coluna1", "Data versão HMP");
        model.addAttribute("coluna2", "Data versão PRD");
        model.addAttribute("valorColuna1", AmbienteEnum.HMP);
        model.addAttribute("valorColuna2", AmbienteEnum.PRD);

        return "sp/" + LISTA_DIFERENCAS;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Optional<Sp> findById(Long Id) {
        return spService.findById(Id);
    }

    @PutMapping
    @RequestMapping(value = "/atualizar")
    public String update(Sp sp, RedirectAttributes redirectAttributes) {
        spService.update(sp);
        redirectAttributes.addFlashAttribute("message", "SP " + sp.getNome() + " atualizada com sucesso!");
        return "redirect:/" + LISTA_SP;
    }

    @DeleteMapping
    @RequestMapping(value = "/excluir")
    public String delete(Sp sp, RedirectAttributes redirectAttributes) {
        spService.delete(sp.getId());
        redirectAttributes.addFlashAttribute("message", "SP " + sp.getNome() + "  excluída com sucesso!");
        return "redirect:/" + LISTA_SP;
    }

    @DeleteMapping
    @RequestMapping(value = "/deleteAll")
    public String deleteAll(RedirectAttributes redirectAttributes) {
        spService.deleteAll();
        redirectAttributes.addFlashAttribute("message", "SPs excluídas com sucesso!");
        return "redirect:/" + LISTA_SP;
    }

    @PostMapping(value = "/equalizarAll/desTqs")
    public String equalizarAllDesTqs(Sp wrapper, RedirectAttributes redirectAttributes) {
        List<Sp> spConverterList = Sp.montarListaSp(wrapper.getSpList());

        for (Sp sp : spConverterList) {
            sp.setDataTqs(sp.getDataDes());
            spService.update(sp);
        }

        redirectAttributes.addFlashAttribute("message", "SPs equalizadas com sucesso!");
        return "redirect:/listaDesTqs";
    }

    @PostMapping(value = "/equalizarAll/tqsHmp")
    public String equalizarAllTqsHmp(Sp wrapper, RedirectAttributes redirectAttributes) {
        List<Sp> spConverterList = Sp.montarListaSp(wrapper.getSpList());

        for (Sp sp : spConverterList) {
            sp.setDataHmp(sp.getDataTqs());
            spService.update(sp);
        }

        redirectAttributes.addFlashAttribute("message", "SPs equalizadas com sucesso!");
        return "redirect:/listaTqsHmp";
    }

    @PostMapping(value = "/equalizarAll/hmpPrd")
    public String equalizarAllHmpPrd(Sp wrapper, RedirectAttributes redirectAttributes) {
        List<Sp> spConverterList = Sp.montarListaSp(wrapper.getSpList());

        for (Sp sp : spConverterList) {
            sp.setDataPrd(sp.getDataHmp());
            spService.update(sp);
        }

        redirectAttributes.addFlashAttribute("message", "SPs equalizadas com sucesso!");
        return "redirect:/listaHmpPrd";
    }

    @PostMapping(value = "/equalizar/desTqs")
    public String equalizarDesTqs(Sp sp, RedirectAttributes redirectAttributes) {
        sp.setDataTqs(sp.getDataDes());
        spService.update(sp);

        redirectAttributes.addFlashAttribute("message", "SP equalizada com sucesso!");
        return "redirect:/listaDesTqs";
    }

    @PostMapping(value = "/equalizar/tqsHmp")
    public String equalizarTqsHmp(Sp sp, RedirectAttributes redirectAttributes) {
        sp.setDataHmp(sp.getDataTqs());
        spService.update(sp);

        redirectAttributes.addFlashAttribute("message", "SP equalizada com sucesso!");
        return "redirect:/listaTqsHmp";
    }

    @PostMapping(value = "/equalizar/hmpPrd")
    public String equalizarHmpPrd(Sp sp, RedirectAttributes redirectAttributes) {
        sp.setDataPrd(sp.getDataHmp());
        spService.update(sp);

        redirectAttributes.addFlashAttribute("message", "SP equalizada com sucesso!");
        return "redirect:/listaHmpPrd";
    }
}