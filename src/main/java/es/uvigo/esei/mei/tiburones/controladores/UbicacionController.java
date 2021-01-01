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

import es.uvigo.esei.mei.tiburones.entidades.Ubicacion;
import es.uvigo.esei.mei.tiburones.servicios.UbicacionService;

@Controller
@RequestMapping("/ubicaciones")
public class UbicacionController {
	@Autowired
	UbicacionService ubicacionService;

	@GetMapping
	public String prepararListarUbicaciones(Model modelo) {
		modelo.addAttribute("ubicaciones", ubicacionService.buscarTodos());
		modelo.addAttribute("descripcionUbicacion", "");
		return "ubicacion/listado_ubicaciones";
	}

	@PostMapping
	public String actualizarListarUbicacion(@RequestParam(required = false) String ciudad,
			@RequestParam(required = false) String pais, Model modelo) {
		List<Ubicacion> ubicaciones;
		if ((ciudad != null) && !ciudad.isEmpty()) {
			ubicaciones = ubicacionService.buscarPorCiudad(ciudad);
		} else if ((pais != null) && !pais.isEmpty()) {
			ubicaciones = ubicacionService.buscarPorPais(pais);
		} else {
			ubicaciones = ubicacionService.buscarTodos();
		}
		modelo.addAttribute("ubicaciones", ubicaciones);	
		return "ubicacion/listado_ubicaciones";
	}

	@GetMapping("nuevo")
	public ModelAndView prepararNuevoUbicacion() {
		Ubicacion ubicacion = new Ubicacion();

		ModelAndView result = new ModelAndView();
		result.addObject("ubicacion", ubicacion);
		result.addObject("esNuevo", true);
		result.setViewName("ubicacion/editar_ubicacion");
		return result;
	}

	@PostMapping("nuevo")
	public String crearUbicacion(@Valid @ModelAttribute Ubicacion ubicacion, BindingResult resultado) {
		if (!resultado.hasErrors()) {
			ubicacionService.crear(ubicacion);
			return "redirect:/ubicaciones";
		} else {
			return null;
		}
	}

	@GetMapping("{id}")
	public String prepararEditarUbicacion(@PathVariable("id") Long id, Model modelo) {
		try {
			Ubicacion ubicacion = ubicacionService.buscarPorId(id);
			modelo.addAttribute("ubicacion", ubicacion);
			modelo.addAttribute("esNuevo", false);			
			return "ubicacion/editar_ubicacion";
		} catch (EntityNotFoundException e) {
			modelo.addAttribute("error", "Ubicacion no encontrado");
			return "error";
		}
	}

	@PostMapping("{id}")
	public String actualizarUbicacion(@Valid @ModelAttribute Ubicacion ubicacion, BindingResult resultado) {
		if (!resultado.hasErrors()) {
			ubicacionService.modificar(ubicacion);
			return "redirect:/ubicaciones";
		} else {
			return null;
		}
	}
	
	/**
	 * @PathVariable vincula el parametro a un segmento de la URI
	 */
	@GetMapping("{id}/eliminar")
	public String borrarUbicacion(@PathVariable("id") Long id, Model modelo) {
		Ubicacion ubicacion = ubicacionService.buscarPorId(id);
		if (ubicacion != null) {
			ubicacionService.eliminar(ubicacion);
			return "redirect:/ubicaciones";
		} else {
			modelo.addAttribute("mensajeError", "Ubicacion no encontrada");
			return "error";
		}
	}
}
