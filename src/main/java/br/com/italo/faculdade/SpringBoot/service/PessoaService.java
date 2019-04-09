package br.com.italo.faculdade.SpringBoot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import br.com.italo.faculdade.SpringBoot.model.Pessoa;
import br.com.italo.faculdade.SpringBoot.repository.PessoaRepository;
import br.com.italo.faculdade.SpringBoot.repository.TelefoneRepository;

@Service
public class PessoaService {
	
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private TelefoneRepository telefoneRepository;

	public ModelAndView listaPessoas() {
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
		andView.addObject("pessoas", pessoasIt);
		andView.addObject("pessoaobj", new Pessoa());
		return andView;
	}

	public ModelAndView startPessoa() {
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		andView.addObject("pessoaobj", new Pessoa());
		return andView;
	}
	
	public ModelAndView startPessoa(String rota) {
		ModelAndView andView = new ModelAndView(rota);
		andView.addObject("pessoaobj", new Pessoa());
		return andView;
	}

	public ModelAndView editarPessoa(Long idpessoa) {
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		andView.addObject("pessoaobj", pessoa.get());
		return andView;
	}
	
	public ModelAndView salvar(@Valid Pessoa pessoa, BindingResult bindingResult) {
		
		/*
		 * if(!bindingResult.hasErrors()) { ModelAndView modelAndView = new
		 * ModelAndView("cadastro/cadastropessoa"); Iterable<Pessoa> pessoasIt =
		 * pessoaRepository.findAll(); modelAndView.addObject("pessoas", pessoasIt);
		 * modelAndView.addObject("pessoaobj", pessoa);
		 * 
		 * List<String> validacao = new ArrayList<String>(); for(ObjectError
		 * objErro:bindingResult.getAllErrors()) {
		 * validacao.add(objErro.getDefaultMessage());//Vem das anotações da class }
		 * modelAndView.addObject("validacao",validacao); return modelAndView; }else {
		 */
		pessoaRepository.save(pessoa);
		return listaPessoas();
		}
	
	
	public ModelAndView pessoaTelefones(Long idpessoa) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
		modelAndView.addObject("pessoaobj",pessoa.get());
		modelAndView.addObject("telefones",telefoneRepository.getTelefones(idpessoa));
		return modelAndView;
	}
	
	public ModelAndView excluir(Long idpessoa) {
		pessoaRepository.deleteById(idpessoa);
		startPessoa().addObject("pessoaobj", pessoaRepository.findAll());
		return listaPessoas();
	}
	
	public ModelAndView consulta(String nomepesquisa) {

		startPessoa().addObject("pessoas", pessoaRepository.findPessoaByName(nomepesquisa));
		return startPessoa();
	}	
}