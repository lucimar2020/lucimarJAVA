package br.biblioteca.livros.controladores;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.model.Autor;
import br.biblioteca.livros.service.AutorService;

@Controller
@RequestMapping("/autor")
public class AutorController {

	@Autowired
	AutorService autorService;

	@GetMapping("/list")
	public ModelAndView autor() {
		ModelAndView modelAndView = new ModelAndView("autor/list");
		List<Autor> listaAutor = autorService.listaAutores();
		modelAndView.addObject("autor", listaAutor);
		return modelAndView;
	}

	@GetMapping("/novo")
	public ModelAndView createForm(@ModelAttribute Autor autor) {
		ModelAndView modelAndView = new ModelAndView("autor/form");
		return modelAndView;
	}

	@PostMapping(value = "/gravar")
	public ModelAndView create(@Valid Autor autor, BindingResult bindingResult) {

		autorService.salvarAutor(autor);
		return new ModelAndView("redirect:/autor/list");
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {

		Autor autor = autorService.buscaAutor(id);

		// List<Autor> listaAutores = autorService.listaAutores();

		ModelAndView modelAndView = new ModelAndView("autor/form");
//		modelAndView.addObject("listaAutores", listaAutores);
		modelAndView.addObject("autor", autor);
		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		autorService.excluiAutor(id);
		return new ModelAndView("redirect:/autor/list");
	}

}
