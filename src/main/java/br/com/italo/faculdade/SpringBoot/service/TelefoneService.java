package br.com.italo.faculdade.SpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.italo.faculdade.SpringBoot.model.Pessoa;
import br.com.italo.faculdade.SpringBoot.model.Telefone;
import br.com.italo.faculdade.SpringBoot.repository.PessoaRepository;
import br.com.italo.faculdade.SpringBoot.repository.TelefoneRepository;

@Service
public class TelefoneService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	
	public ModelAndView listaTelefones(Telefone telefone, @PathVariable("pessoaid") Long pessoaid) {
		Pessoa pessoa = pessoaRepository.findById(pessoaid).get();
		telefone.setPessoa(pessoa);
		telefoneRepository.save(telefone);
		
		ModelAndView modelAndView= new ModelAndView("cadastro/telefones");
		modelAndView.addObject("pessoaobj",pessoa);
		modelAndView.addObject("telefones",telefoneRepository.getTelefones(pessoaid));
		return modelAndView;
	}
}
	
