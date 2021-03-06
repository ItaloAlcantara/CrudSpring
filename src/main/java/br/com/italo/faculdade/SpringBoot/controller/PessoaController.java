package br.com.italo.faculdade.SpringBoot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.italo.faculdade.SpringBoot.model.Pessoa;
import br.com.italo.faculdade.SpringBoot.model.Telefone;
import br.com.italo.faculdade.SpringBoot.repository.PessoaRepository;
import br.com.italo.faculdade.SpringBoot.repository.TelefoneRepository;
import br.com.italo.faculdade.SpringBoot.service.PessoaService;

@Controller
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private PessoaService pessoaService;
	

	@GetMapping(value = "/cadastropessoa")
	public ModelAndView inicio() {
		return pessoaService.listaPessoas();
	}

	@PostMapping(value = "**/salvarpessoa")
	public ModelAndView salvar(Pessoa pessoa,BindingResult bindingResult) {
		return pessoaService.salvar(pessoa, bindingResult);
	}

	@GetMapping(value = "/listapessoas")
	public ModelAndView pessoasLista() {
		return pessoaService.listaPessoas();
	}

	@GetMapping(value = "/editarpessoa/{idpessoa}")
	public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa) {
		return pessoaService.editarPessoa(idpessoa);
	}
	
	@GetMapping(value="/telefones/{idpessoa}")
	public ModelAndView telefones(@PathVariable("idpessoa") Long idpessoa) {
		
		return pessoaService.pessoaTelefones(idpessoa);
	}
	

	@GetMapping(value = "/excluirpessoa/{idpessoa}")
	public ModelAndView excluir(@PathVariable("idpessoa") Long idpessoa) {
		return pessoaService.excluir(idpessoa);
	}

	@PostMapping("**/nomepesquisa")
	public ModelAndView consulta(@RequestParam("nomepesquisa") String nomepesquisa) {
		return pessoaService.consulta(nomepesquisa);

	}
	

}
