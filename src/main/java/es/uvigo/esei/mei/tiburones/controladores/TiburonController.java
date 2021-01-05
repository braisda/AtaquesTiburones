package es.uvigo.esei.mei.tiburones.controladores;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.uvigo.esei.mei.tiburones.entidades.Investigador;
import es.uvigo.esei.mei.tiburones.entidades.Tiburon;
import es.uvigo.esei.mei.tiburones.servicios.TiburonService;

@Controller
@RequestMapping("/tiburones")
public class TiburonController {
	@Autowired
	TiburonService tiburonService;

	@GetMapping
	public String prepararListarTiburones(Model modelo) {
		modelo.addAttribute("tiburones", tiburonService.buscarTodos());
		modelo.addAttribute("investigadores", tiburonService.buscarInvestigadores());
		modelo.addAttribute("descripcionTiburon", "");
		return "tiburon/listado_tiburones";
	}

	@PostMapping
	public String actualizarListarTiburon(@RequestParam(required = false) String raza,
			@RequestParam(required = false) String tamanho,
			@RequestParam(required = false) String edad,
			@RequestParam(required = false) Long idInvestigador, Model modelo) {
		List<Tiburon> tiburones;
		if ((raza != null) && !raza.isEmpty()) {
			tiburones = tiburonService.buscarPorRaza(raza);
		}else if ((tamanho != null) && !tamanho.isEmpty()) {
			tiburones = tiburonService.buscarPorTamanho(tamanho);
		}else if ((edad != null) && !edad.isEmpty()) {
			tiburones = tiburonService.buscarPorEdad(edad);
		} else if (idInvestigador != null) {
			tiburones = tiburonService.buscarPorInvestigador(idInvestigador);
		} else {
			tiburones = tiburonService.buscarTodos();
		}
		modelo.addAttribute("tiburones", tiburones);
		modelo.addAttribute("investigadores", tiburonService.buscarInvestigadores());		
		return "tiburon/listado_tiburones";
	}

	@GetMapping("nuevo")
	public ModelAndView prepararNuevoTiburon() {
		Tiburon tiburon = new Tiburon();

		ModelAndView result = new ModelAndView();
		result.addObject("tiburon", tiburon);
		result.addObject("esNuevo", true);
		result.addObject("investigadores", tiburonService.buscarInvestigadores());
		result.setViewName("tiburon/editar_tiburon");
		return result;
	}

	@PostMapping("nuevo")
	public String crearTiburon(@Valid @ModelAttribute Tiburon tiburon, BindingResult resultado) {
		if (!resultado.hasErrors()) {
			tiburonService.crear(tiburon);
			return "redirect:/tiburones";
		} else {
			return null;
		}
	}

	@GetMapping("{id}")
	public String prepararEditarTiburon(@PathVariable("id") Long id, Model modelo) {
		try {
			Tiburon tiburon = tiburonService.buscarPorId(id);
			modelo.addAttribute("tiburon", tiburon);
			modelo.addAttribute("esNuevo", false);
			modelo.addAttribute("investigadores", tiburonService.buscarInvestigadores());			
			return "tiburon/editar_tiburon";
		} catch (EntityNotFoundException e) {
			modelo.addAttribute("error", "Artículo no encontrado");
			return "error";
		}
	}

	@PostMapping("{id}")
	public String actualizarTiburon(@Valid @ModelAttribute Tiburon tiburon, BindingResult resultado) {
		if (!resultado.hasErrors()) {
			tiburonService.modificar(tiburon);
			return "redirect:/tiburones";
		} else {
			return null;
		}
	}
	
	/**
	 * @PathVariable vincula el parametro a un segmento de la URI
	 */
	@GetMapping("{id}/eliminar")
	public String borrarTiburon(@PathVariable("id") Long id, Model modelo) {
		Tiburon tiburon = tiburonService.buscarPorId(id);
		if (tiburon != null) {
			tiburonService.eliminar(tiburon);
			return "redirect:/tiburones";
		} else {
			modelo.addAttribute("mensajeError", "Tiburon no encontrado");
			return "error";
		}
	}
}
