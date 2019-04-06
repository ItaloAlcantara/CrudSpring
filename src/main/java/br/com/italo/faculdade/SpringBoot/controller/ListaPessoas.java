package br.com.italo.faculdade.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import br.com.italo.faculdade.SpringBoot.model.Pessoa;
import br.com.italo.faculdade.SpringBoot.repository.PessoaRepository;

@Controller
public class ListaPessoas {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public ModelAndView listaPessoas() {
	ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
	Iterable<Pessoa> pessoasIt=pessoaRepository.findAll();
	andView.addObject("pessoas", pessoasIt);
	andView.addObject("pessoaobj", new Pessoa());
	return  andView;
	}
	
	public ModelAndView start() {
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		andView.addObject("pessoaobj", new Pessoa());
		return andView;
	}
	
}
