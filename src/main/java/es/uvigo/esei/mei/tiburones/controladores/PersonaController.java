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

import es.uvigo.esei.mei.tiburones.entidades.Persona;
import es.uvigo.esei.mei.tiburones.servicios.PersonaService;

@Controller
@RequestMapping("/personas")
public class PersonaController {
	@Autowired
	PersonaService personaService;

	@GetMapping
	public String prepararListarPersonas(Model modelo) {
		modelo.addAttribute("personas", personaService.buscarTodos());
		modelo.addAttribute("descripcionPersona", "");
		return "persona/listado_personas";
	}

	@PostMapping
	public String actualizarListarPersona(@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String paisNacimiento,
			@RequestParam(required = false) String fechaNacimiento, Model modelo) {
		List<Persona> personas;
		if ((nombre != null) && !nombre.isEmpty()) {
			personas = personaService.buscarPorNombre(nombre);
		} else if ((paisNacimiento != null) && !paisNacimiento.isEmpty()) {
			personas = personaService.buscarPorPaisNacimiento(paisNacimiento);
		} else if ((fechaNacimiento != null) && !fechaNacimiento.isEmpty()) {
			personas = personaService.buscarPorFechaNacimiento(fechaNacimiento);
		} else {
			personas = personaService.buscarTodos();
		}
		modelo.addAttribute("personas", personas);	
		return "persona/listado_personas";
	}

	@GetMapping("nuevo")
	public ModelAndView prepararNuevoPersona() {
		Persona persona = new Persona();

		ModelAndView result = new ModelAndView();
		result.addObject("persona", persona);
		result.addObject("esNuevo", true);
		result.setViewName("persona/editar_persona");
		return result;
	}

	@PostMapping("nuevo")
	public String crearPersona(@Valid @ModelAttribute Persona persona, BindingResult resultado) {
		if (!resultado.hasErrors()) {
			personaService.crear(persona);
			return "redirect:/personas";
		} else {
			return null;
		}
	}

	@GetMapping("{id}")
	public String prepararEditarPersona(@PathVariable("id") Long id, Model modelo) {
		try {
			Persona persona = personaService.buscarPorId(id);
			modelo.addAttribute("persona", persona);
			modelo.addAttribute("esNuevo", false);			
			return "persona/editar_persona";
		} catch (EntityNotFoundException e) {
			modelo.addAttribute("error", "Persona no encontrado");
			return "error";
		}
	}

	@PostMapping("{id}")
	public String actualizarPersona(@Valid @ModelAttribute Persona persona, BindingResult resultado) {
		if (!resultado.hasErrors()) {
			personaService.modificar(persona);
			return "redirect:/personas";
		} else {
			return null;
		}
	}
	
	/**
	 * @PathVariable vincula el parametro a un segmento de la URI
	 */
	@GetMapping("{id}/eliminar")
	public String borrarPersona(@PathVariable("id") Long id, Model modelo) {
		Persona persona = personaService.buscarPorId(id);
		if (persona != null) {
			personaService.eliminar(persona);
			return "redirect:/personas";
		} else {
			modelo.addAttribute("mensajeError", "Persona no encontrada");
			return "error";
		}
	}
}
