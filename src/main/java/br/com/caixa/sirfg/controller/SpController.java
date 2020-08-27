package br.com.caixa.sirfg.controller;

import br.com.caixa.sirfg.model.Ambiente;
import br.com.caixa.sirfg.model.InformacaoSp;
import br.com.caixa.sirfg.model.Sp;
import br.com.caixa.sirfg.model.enumerator.AmbienteEnum;
import br.com.caixa.sirfg.model.enumerator.TipoObjetoEnum;
import br.com.caixa.sirfg.service.AmbienteService;
import br.com.caixa.sirfg.service.SpService;
import br.com.caixa.sirfg.util.Constantes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SpController {
    private final SpService spService;
    private final AmbienteService ambienteService;

    private List<Sp> sps = new ArrayList<>();
    private List<Sp> spsNr = new ArrayList<>();
    private List<Sp> spsCargaNr = new ArrayList<>();
    private List<Sp> bindNr = new ArrayList<>();
    private List<Sp> cobolNr = new ArrayList<>();
    private List<Sp> jclNr = new ArrayList<>();

    public SpController(SpService spService, AmbienteService ambienteService) {
        this.spService = spService;
        this.ambienteService = ambienteService;
    }

    @RequestMapping("/listaSp")
    public String findAll(Model model) {
        model.addAttribute("versaoCadastrada", false);
        List<Ambiente> ambienteList = ambienteService.findAll();

        if (!ambienteList.isEmpty()) {
            Ambiente ambiente = ambienteList.get(0);

            String versaoDes = "Versão de DES: " + ambiente.getVersaoDes() + " - " + ambiente.getDataFormatada(AmbienteEnum.DES);
            String versaoTqs = "Versão de TQS: " + ambiente.getVersaoTqs() + " - " + ambiente.getDataFormatada(AmbienteEnum.TQS);
            String versaoHmp = "Versão de HMP: " + ambiente.getVersaoHmp() + " - " + ambiente.getDataFormatada(AmbienteEnum.HMP);
            String versaoPrd = "Versão de PRD: " + ambiente.getVersaoPrd() + " - " + ambiente.getDataFormatada(AmbienteEnum.PRD);

            model.addAttribute("versaoCadastrada", true);
            model.addAttribute("versaoDes", versaoDes);
            model.addAttribute("versaoTqs", versaoTqs);
            model.addAttribute("versaoHmp", versaoHmp);
            model.addAttribute("versaoPrd", versaoPrd);
        }

        sps = spService.findAll();

        model.addAttribute("ambiente", "home");
        model.addAttribute("sps", sps);

        return "sp/listaCompleta";
    }

    @RequestMapping("/listaDesTqs")
    public String findAllDesTqs(Model model) {
        sps = spService.findAllDesTqs();

        model.addAttribute("titulo", Constantes.TITULO_DES_TQS);
        model.addAttribute("ambiente", "desTqs");
        model.addAttribute("actionEqualizar", "/equalizar/desTqs");
        model.addAttribute("sps", sps);
        model.addAttribute("coluna1", Constantes.DATA_VERSAO_DES);
        model.addAttribute("coluna2", Constantes.DATA_VERSAO_TQS);
        model.addAttribute("valorColuna1", AmbienteEnum.DES);
        model.addAttribute("valorColuna2", AmbienteEnum.TQS);

        return Constantes.LISTA_DIFERENCAS;
    }

    @RequestMapping("/listaTqsHmp")
    public String findAllTqsHmp(Model model) {
        sps = spService.findAllTqsHmp();

        agruparSps();

        model.addAttribute("titulo", Constantes.TITULO_TQS_HMP);
        model.addAttribute("ambiente", "tqsHmp");
        model.addAttribute("actionEqualizar", "/equalizar/tqsHmp");
        model.addAttribute("sps", sps);
        model.addAttribute("spsNr", spsNr);
        model.addAttribute("spsCargaNr", spsCargaNr);
        model.addAttribute("bindNr", bindNr);
        model.addAttribute("cobolNr", cobolNr);
        model.addAttribute("jclNr", jclNr);
        model.addAttribute("coluna1", Constantes.DATA_VERSAO_TQS);
        model.addAttribute("coluna2", Constantes.DATA_VERSAO_HMP);
        model.addAttribute("valorColuna1", AmbienteEnum.TQS);
        model.addAttribute("valorColuna2", AmbienteEnum.HMP);

        return Constantes.LISTA_DIFERENCAS;
    }

    @RequestMapping("/listaHmpPrd")
    public String findAllHmpPrd(Model model) {
        sps = spService.findAllHmpPrd();

        agruparSps();

        model.addAttribute("titulo", Constantes.TITULO_HMP_PRD);
        model.addAttribute("ambiente", "hmpPrd");
        model.addAttribute("actionEqualizar", "/equalizar/hmpPrd");
        model.addAttribute("sps", sps);
        model.addAttribute("spsNr", spsNr);
        model.addAttribute("spsCargaNr", spsCargaNr);
        model.addAttribute("bindNr", bindNr);
        model.addAttribute("cobolNr", cobolNr);
        model.addAttribute("jclNr", jclNr);
        model.addAttribute("coluna1", Constantes.DATA_VERSAO_HMP);
        model.addAttribute("coluna2", Constantes.DATA_VERSAO_PRD);
        model.addAttribute("valorColuna1", AmbienteEnum.HMP);
        model.addAttribute("valorColuna2", AmbienteEnum.PRD);

        return Constantes.LISTA_DIFERENCAS;
    }

    @PostMapping("/adicionar")
    public String create(Sp sp, InformacaoSp informacaoSp, RedirectAttributes redirectAttributes) {
        String nomeObejto = sp.getNome().toUpperCase();
        List<Sp> spList = spService.findAllByNome(nomeObejto);

        for (Sp sp1 : spList) {
            if (sp1.getTipoObjeto() == sp.getTipoObjeto()) {
                redirectAttributes.addFlashAttribute("success", false);
                redirectAttributes.addFlashAttribute("message", "Já existe um objeto do tipo " + sp.getTipoObjeto().getDescricaoObjeto() + " com o nome " + nomeObejto + " cadastrado!");
                return Constantes.REDIRECT_LISTA_SP;
            }
        }

        informacaoSp.setDataManipulacao(LocalDateTime.now());
        sp.getInformacoes().add(informacaoSp);

        spService.createOrUpdate(sp);

        redirectAttributes.addFlashAttribute("success", true);
        redirectAttributes.addFlashAttribute("message", "Objeto " + nomeObejto + " cadastrado com sucesso!");

        return Constantes.REDIRECT_LISTA_SP;
    }

    @PostMapping(value = "/atualizar")
    public String update(Sp sp, InformacaoSp informacaoSp, RedirectAttributes redirectAttributes) {
        String nomeObejto = sp.getNome().toUpperCase();
        List<Sp> spList = spService.findAllByNome(nomeObejto);

        for (Sp sp1 : spList) {
            if (!sp1.getId().equals(sp.getId())
                    && sp1.getNome().equalsIgnoreCase(nomeObejto)
                    && sp1.getTipoObjeto() == sp.getTipoObjeto()) {
                redirectAttributes.addFlashAttribute("success", false);
                redirectAttributes.addFlashAttribute("message", "Já existe um objeto do tipo " + sp.getTipoObjeto().getDescricaoObjeto() + " com o nome " + nomeObejto + " cadastrado!");
                return Constantes.REDIRECT_LISTA_SP;
            }
        }

        sp.setInformacoes(spService.obterHistoricoObjeto(sp.getId()));
        informacaoSp.setId(null);
        informacaoSp.setDataManipulacao(LocalDateTime.now());
        sp.getInformacoes().add(informacaoSp);

        spService.createOrUpdate(sp);

        redirectAttributes.addFlashAttribute("success", true);
        redirectAttributes.addFlashAttribute("message", "Objeto " + nomeObejto + " atualizado com sucesso!");

        return Constantes.REDIRECT_LISTA_SP;
    }

    @PostMapping(value = "/excluir")
    public String delete(Sp sp, RedirectAttributes redirectAttributes) {
        String nomeSp = sp.getNome();

        spService.delete(sp.getId());

        redirectAttributes.addFlashAttribute("success", true);
        redirectAttributes.addFlashAttribute("message", "Objeto " + nomeSp + "  excluído com sucesso!");

        return Constantes.REDIRECT_LISTA_SP;
    }

    @PostMapping(value = "/equalizar/desTqs")
    public String equalizarDesTqs(Sp sp, RedirectAttributes redirectAttributes) {
        sp.setDataTqs(sp.getDataDes());

        equalizar(sp, redirectAttributes);

        return Constantes.REDIRECT_LISTA_DES_TQS;
    }

    @PostMapping(value = "/equalizar/tqsHmp")
    public String equalizarTqsHmp(Sp sp, RedirectAttributes redirectAttributes) {
        sp.setDataHmp(sp.getDataTqs());

        equalizar(sp, redirectAttributes);

        return Constantes.REDIRECT_LISTA_TQS_HMP;
    }

    @PostMapping(value = "/equalizar/hmpPrd")
    public String equalizarHmpPrd(Sp sp, RedirectAttributes redirectAttributes) {
        sp.setDataPrd(sp.getDataHmp());

        equalizar(sp, redirectAttributes);

        return Constantes.REDIRECT_LISTA_HMP_PRD;
    }

    private void agruparSps() {
        spsNr = spService.agruparSps(spsNr, sps, TipoObjetoEnum.SP);
        spsCargaNr = spService.agruparSps(spsCargaNr, sps, TipoObjetoEnum.SP_CARGA);
        bindNr = spService.agruparSps(bindNr, sps, TipoObjetoEnum.BIND);
        cobolNr = spService.agruparSps(cobolNr, sps, TipoObjetoEnum.COBOL);
        jclNr = spService.agruparSps(jclNr, sps, TipoObjetoEnum.JCL);
    }

    private void equalizar(Sp sp, RedirectAttributes redirectAttributes) {
        spService.createOrUpdate(sp);

        redirectAttributes.addFlashAttribute("message", "Objeto " + sp.getNome() + " equalizado com sucesso!");
    }
}