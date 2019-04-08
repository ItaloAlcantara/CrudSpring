package br.com.italo.faculdade.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.italo.faculdade.SpringBoot.model.Pessoa;
import br.com.italo.faculdade.SpringBoot.model.Telefone;
import br.com.italo.faculdade.SpringBoot.repository.TelefoneRepository;
import br.com.italo.faculdade.SpringBoot.service.TelefoneService;


@Controller
public class TelefoneController {
	
		
	@Autowired
	private TelefoneService telefoneService;
	@Autowired
	private TelefoneRepository telefoneRepository;
	@PostMapping("**/addTelefonePessoa/{pessoaid}")
	public ModelAndView addTelefonePessoa(Telefone telefone, @PathVariable("pessoaid") Long pessoaid) {
		
		return telefoneService.addTelefones(telefone, pessoaid);
		
	}
	
	@GetMapping("/excluirtelefone/{idtelefone}")
	public ModelAndView removeTelefone(@PathVariable("idtelefone") Long idtelefone) {
		return telefoneService.removeTelefone(idtelefone);
		
	}
}
