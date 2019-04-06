package br.com.italo.faculdade.SpringBoot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.italo.faculdade.SpringBoot.model.Pessoa;
import br.com.italo.faculdade.SpringBoot.repository.PessoaRepository;
import br.com.italo.faculdade.SpringBoot.service.PessoaService;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private PessoaService lista;
	
	@GetMapping(value="/cadastropessoa")
	public ModelAndView inicio() {

		return  lista.listaPessoas();
	}
	
	@PostMapping(value="**/salvarpessoa")
	public ModelAndView salvar(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		return  lista.listaPessoas();
	}
	
	@GetMapping(value="/listapessoas")
	public ModelAndView pessoas() {
		return lista.listaPessoas();
	}
	
	@GetMapping(value="/editarpessoa/{idpessoa}")
	public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa) {
		Optional<Pessoa> pessoa=pessoaRepository.findById(idpessoa);
		
		lista.start().addObject("pessoaobj",pessoa.get());
		return lista.start();
	}
	
	@GetMapping(value="/excluirpessoa/{idpessoa}")
	public ModelAndView excluir (@PathVariable("idpessoa") Long idpessoa) {
		pessoaRepository.deleteById(idpessoa);
		lista.start().addObject("pessoaobj",pessoaRepository.findAll());
		return lista.listaPessoas();
	}
	
	@PostMapping("**/nomepesquisa")
	public ModelAndView consulta (@RequestParam("nomepesquisa") String nomepesquisa) {

		lista.start().addObject("pessoas",pessoaRepository.findPessoaByName(nomepesquisa));
		return lista.start();
		
	}
	
}

