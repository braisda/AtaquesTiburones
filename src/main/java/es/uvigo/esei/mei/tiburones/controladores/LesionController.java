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

import es.uvigo.esei.mei.tiburones.entidades.Lesion;
import es.uvigo.esei.mei.tiburones.servicios.LesionService;

@Controller
@RequestMapping("/lesiones")
public class LesionController {
	@Autowired
	LesionService lesionService;

	@GetMapping
	public String prepararListarLesiones(Model modelo) {
		modelo.addAttribute("lesiones", lesionService.buscarTodos());
		modelo.addAttribute("ataques", lesionService.buscarAtaques());
		modelo.addAttribute("hospitales", lesionService.buscarHospitales());
		modelo.addAttribute("descripcionLesion", "");
		return "lesion/listado_lesiones";
	}

	@PostMapping
	public String actualizarListarLesion(@RequestParam(required = false) String tipo,
			@RequestParam(required = false) String mortal,
			@RequestParam(required = false) String descripcion,
			@RequestParam(required = false) Long ataque,
			@RequestParam(required = false) Long hospital, Model modelo) {
		List<Lesion> lesiones;
		if ((tipo != null) && !tipo.isEmpty()) {
			lesiones = lesionService.buscarPorTipo(tipo);
		} else if ((mortal != null) && !mortal.isEmpty()) {
			lesiones = lesionService.buscarPorMortal(mortal);
		} else if ((descripcion != null) && !descripcion.isEmpty()) {
			lesiones = lesionService.buscarPorDescripcion(descripcion);
		}else if ((ataque != null)) {
			lesiones = lesionService.buscarPorAtaque(ataque);
		}else if ((hospital != null)) {
			lesiones = lesionService.buscarPorHospital(hospital);
		} else {
			lesiones = lesionService.buscarTodos();
		}
		modelo.addAttribute("lesiones", lesiones);	
		modelo.addAttribute("ataques", lesionService.buscarAtaques());
		modelo.addAttribute("hospitales", lesionService.buscarHospitales());
		return "lesion/listado_lesiones";
	}

	@GetMapping("nuevo")
	public ModelAndView prepararNuevoLesion() {
		Lesion lesion = new Lesion();

		ModelAndView result = new ModelAndView();
		result.addObject("lesion", lesion);
		result.addObject("esNuevo", true);
		result.addObject("ataques", lesionService.buscarAtaques());
		result.addObject("hospitales", lesionService.buscarHospitales());
		result.setViewName("lesion/editar_lesion");
		return result;
	}

	@PostMapping("nuevo")
	public String crearLesion(@Valid @ModelAttribute Lesion lesion, BindingResult resultado) {
		if (!resultado.hasErrors()) {
			lesionService.crear(lesion);
			return "redirect:/lesiones";
		} else {
			return null;
		}
	}

	@GetMapping("{id}")
	public String prepararEditarLesion(@PathVariable("id") Long id, Model modelo) {
		try {
			Lesion lesion = lesionService.buscarPorId(id);
			modelo.addAttribute("lesion", lesion);
			modelo.addAttribute("esNuevo", false);
			modelo.addAttribute("ataques", lesionService.buscarAtaques());
			modelo.addAttribute("hospitales", lesionService.buscarHospitales());
			return "lesion/editar_lesion";
		} catch (EntityNotFoundException e) {
			modelo.addAttribute("error", "Lesion no encontrada");
			return "error";
		}
	}

	@PostMapping("{id}")
	public String actualizarLesion(@Valid @ModelAttribute Lesion lesion, BindingResult resultado) {
		if (!resultado.hasErrors()) {
			lesionService.modificar(lesion);
			return "redirect:/lesiones";
		} else {
			return null;
		}
	}
	
	/**
	 * @PathVariable vincula el parametro a un segmento de la URI
	 */
	@GetMapping("{id}/eliminar")
	public String borrarLesion(@PathVariable("id") Long id, Model modelo) {
		Lesion lesion = lesionService.buscarPorId(id);
		if (lesion != null) {
			lesionService.eliminar(lesion);
			return "redirect:/lesiones";
		} else {
			modelo.addAttribute("mensajeError", "Lesion no encontrada");
			return "error";
		}
	}
}
