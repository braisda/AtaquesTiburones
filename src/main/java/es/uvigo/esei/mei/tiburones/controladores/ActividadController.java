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

import es.uvigo.esei.mei.tiburones.entidades.Actividad;
import es.uvigo.esei.mei.tiburones.servicios.ActividadService;

@Controller
@RequestMapping("/actividades")
public class ActividadController {
	@Autowired
	ActividadService actividadService;

	@GetMapping
	public String prepararListarActividades(Model modelo) {
		modelo.addAttribute("actividades", actividadService.buscarTodos());
		modelo.addAttribute("descripcionActividad", "");
		return "actividad/listado_actividades";
	}

	@PostMapping
	public String actualizarListarActividad(@RequestParam(required = false) String tipo,
			Model modelo) {
		List<Actividad> actividades;
		if ((tipo != null) && !tipo.isEmpty()) {
			actividades = actividadService.buscarPorTipo(tipo);
		} else {
			actividades = actividadService.buscarTodos();
		}
		modelo.addAttribute("actividades", actividades);	
		return "actividad/listado_actividades";
	}

	@GetMapping("nuevo")
	public ModelAndView prepararNuevoActividad() {
		Actividad actividad = new Actividad();

		ModelAndView result = new ModelAndView();
		result.addObject("actividad", actividad);
		result.addObject("esNuevo", true);
		result.setViewName("actividad/editar_actividad");
		return result;
	}

	@PostMapping("nuevo")
	public String crearActividad(@Valid @ModelAttribute Actividad actividad, BindingResult resultado) {
		if (!resultado.hasErrors()) {
			actividadService.crear(actividad);
			return "redirect:/actividades";
		} else {
			return null;
		}
	}

	@GetMapping("{id}")
	public String prepararEditarActividad(@PathVariable("id") Long id, Model modelo) {
		try {
			Actividad actividad = actividadService.buscarPorId(id);
			modelo.addAttribute("actividad", actividad);
			modelo.addAttribute("esNuevo", false);			
			return "actividad/editar_actividad";
		} catch (EntityNotFoundException e) {
			modelo.addAttribute("error", "Actividad no encontrado");
			return "error";
		}
	}

	@PostMapping("{id}")
	public String actualizarActividad(@Valid @ModelAttribute Actividad actividad, BindingResult resultado) {
		if (!resultado.hasErrors()) {
			actividadService.modificar(actividad);
			return "redirect:/actividades";
		} else {
			return null;
		}
	}
	
	/**
	 * @PathVariable vincula el parametro a un segmento de la URI
	 */
	@GetMapping("{id}/eliminar")
	public String borrarActividad(@PathVariable("id") Long id, Model modelo) {
		Actividad actividad = actividadService.buscarPorId(id);
		if (actividad != null) {
			actividadService.eliminar(actividad);
			return "redirect:/actividades";
		} else {
			modelo.addAttribute("mensajeError", "Actividad no encontrada");
			return "error";
		}
	}
}
