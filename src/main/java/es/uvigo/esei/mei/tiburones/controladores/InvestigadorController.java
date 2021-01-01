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
import es.uvigo.esei.mei.tiburones.servicios.InvestigadorService;

@Controller
@RequestMapping("/investigadores")
public class InvestigadorController {
	@Autowired
	InvestigadorService investigadorService;

	@GetMapping
	public String prepararListarInvestigadores(Model modelo) {
		modelo.addAttribute("investigadores", investigadorService.buscarTodos());
		modelo.addAttribute("descripcionInvestigador", "");
		return "investigador/listado_investigadores";
	}

	@PostMapping
	public String actualizarListarInvestigador(@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String institucion, Model modelo) {
		List<Investigador> investigadores;
		if ((nombre != null) && !nombre.isEmpty()) {
			investigadores = investigadorService.buscarPorNombre(nombre);
		} else if ((institucion != null) && !institucion.isEmpty()) {
			investigadores = investigadorService.buscarPorInstitucion(institucion);
		} else {
			investigadores = investigadorService.buscarTodos();
		}
		modelo.addAttribute("investigadores", investigadores);	
		return "investigador/listado_investigadores";
	}

	@GetMapping("nuevo")
	public ModelAndView prepararNuevoInvestigador() {
		Investigador investigador = new Investigador();

		ModelAndView result = new ModelAndView();
		result.addObject("investigador", investigador);
		result.addObject("esNuevo", true);
		result.setViewName("investigador/editar_investigador");
		return result;
	}

	@PostMapping("nuevo")
	public String crearInvestigador(@Valid @ModelAttribute Investigador investigador, BindingResult resultado) {
		if (!resultado.hasErrors()) {
			investigadorService.crear(investigador);
			return "redirect:/investigadores";
		} else {
			return null;
		}
	}

	@GetMapping("{id}")
	public String prepararEditarInvestigador(@PathVariable("id") Long id, Model modelo) {
		try {
			Investigador investigador = investigadorService.buscarPorId(id);
			modelo.addAttribute("investigador", investigador);
			modelo.addAttribute("esNuevo", false);
			//modelo.addAttribute("familias", investigadorService.buscarFamilias());			
			return "investigador/editar_investigador";
		} catch (EntityNotFoundException e) {
			modelo.addAttribute("error", "Investigador no encontrado");
			return "error";
		}
	}

	@PostMapping("{id}")
	public String actualizarInvestigador(@Valid @ModelAttribute Investigador investigador, BindingResult resultado) {
		if (!resultado.hasErrors()) {
			investigadorService.modificar(investigador);
			return "redirect:/investigadores";
		} else {
			return null;
		}
	}
	
	/**
	 * @PathVariable vincula el parametro a un segmento de la URI
	 */
	@GetMapping("{id}/eliminar")
	public String borrarCliente(@PathVariable("id") Long id, Model modelo) {
		Investigador investigador = investigadorService.buscarPorId(id);
		if (investigador != null) {
			investigadorService.eliminar(investigador);
			return "redirect:/investigadores";
		} else {
			modelo.addAttribute("mensajeError", "Investigador no encontrado");
			return "error";
		}
	}
}
