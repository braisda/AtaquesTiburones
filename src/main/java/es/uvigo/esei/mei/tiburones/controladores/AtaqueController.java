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

import es.uvigo.esei.mei.tiburones.entidades.Ataque;
import es.uvigo.esei.mei.tiburones.servicios.AtaqueService;

@Controller
@RequestMapping("/ataques")
public class AtaqueController {
	@Autowired
	AtaqueService ataqueService;

	@GetMapping
	public String prepararListarAtaques(Model modelo) {
		modelo.addAttribute("ataques", ataqueService.buscarTodos());
		modelo.addAttribute("tiburones", ataqueService.buscarTiburones());
		modelo.addAttribute("actividades", ataqueService.buscarActividades());
		modelo.addAttribute("ubicaciones", ataqueService.buscarUbicaciones());
		modelo.addAttribute("personas", ataqueService.buscarPersonas());
		modelo.addAttribute("descripcionAtaque", "");
		return "ataque/listado_ataques";
	}

	@PostMapping
	public String actualizarListarAtaque(@RequestParam(required = false) String fecha,
			@RequestParam(required = false) Long tiburon,
			@RequestParam(required = false) Long actividad,
			@RequestParam(required = false) Long ubicacion,
			@RequestParam(required = false) Long persona, Model modelo) {
		List<Ataque> ataques;
		if ((fecha != null) && !fecha.isEmpty()) {
			ataques = ataqueService.buscarPorFecha(fecha);
		} else if (tiburon != null) {
			ataques = ataqueService.buscarPorTiburon(tiburon);
		} else if (actividad != null) {
			ataques = ataqueService.buscarPorActividad(actividad);
		}else if (ubicacion != null) {
			ataques = ataqueService.buscarPorUbicacion(ubicacion);
		}else if (persona != null) {
			ataques = ataqueService.buscarPorPersona(persona);
		}else {
			ataques = ataqueService.buscarTodos();
		}
		modelo.addAttribute("ataques", ataques);
		modelo.addAttribute("tiburones", ataqueService.buscarTiburones());
		modelo.addAttribute("actividades", ataqueService.buscarActividades());
		modelo.addAttribute("ubicaciones", ataqueService.buscarUbicaciones());
		modelo.addAttribute("personas", ataqueService.buscarPersonas());
		return "ataque/listado_ataques";
	}

	@GetMapping("nuevo")
	public ModelAndView prepararNuevoAtaque() {
		Ataque ataque = new Ataque();

		ModelAndView result = new ModelAndView();
		result.addObject("ataque", ataque);
		result.addObject("esNuevo", true);
		result.addObject("tiburones", ataqueService.buscarTiburones());
		result.addObject("actividades", ataqueService.buscarActividades());
		result.addObject("ubicaciones", ataqueService.buscarUbicaciones());
		result.addObject("personas", ataqueService.buscarPersonas());
		result.setViewName("ataque/editar_ataque");
		return result;
	}

	@PostMapping("nuevo")
	public String crearAtaque(@Valid @ModelAttribute Ataque ataque, BindingResult resultado) {
		if (!resultado.hasErrors()) {
			ataqueService.crear(ataque);
			return "redirect:/ataques";
		} else {
			return null;
		}
	}

	@GetMapping("{id}")
	public String prepararEditarAtaque(@PathVariable("id") Long id, Model modelo) {
		try {
			Ataque ataque = ataqueService.buscarPorId(id);
			modelo.addAttribute("ataque", ataque);
			modelo.addAttribute("esNuevo", false);
			modelo.addAttribute("tiburones", ataqueService.buscarTiburones());
			modelo.addAttribute("actividades", ataqueService.buscarActividades());
			modelo.addAttribute("ubicaciones", ataqueService.buscarUbicaciones());
			modelo.addAttribute("personas", ataqueService.buscarPersonas());		
			return "ataque/editar_ataque";
		} catch (EntityNotFoundException e) {
			modelo.addAttribute("error", "Artículo no encontrado");
			return "error";
		}
	}

	@PostMapping("{id}")
	public String actualizarAtaque(@Valid @ModelAttribute Ataque ataque, BindingResult resultado) {
		if (!resultado.hasErrors()) {
			ataqueService.modificar(ataque);
			return "redirect:/ataques";
		} else {
			return null;
		}
	}
	
	/**
	 * @PathVariable vincula el parametro a un segmento de la URI
	 */
	@GetMapping("{id}/eliminar")
	public String borrarAtaque(@PathVariable("id") Long id, Model modelo) {
		Ataque ataque = ataqueService.buscarPorId(id);
		if (ataque != null) {
			ataqueService.eliminar(ataque);
			return "redirect:/ataques";
		} else {
			modelo.addAttribute("mensajeError", "Ataque no encontrado");
			return "error";
		}
	}
}
