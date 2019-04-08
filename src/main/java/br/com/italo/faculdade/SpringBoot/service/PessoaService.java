package br.com.italo.faculdade.SpringBoot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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

	public ModelAndView editarPessoa(@PathVariable("idpessoa") Long idpessoa) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		startPessoa().addObject("pessoaobj", pessoa.get());
		return startPessoa();
	}
	
	public ModelAndView salvar(Pessoa pessoa) {
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