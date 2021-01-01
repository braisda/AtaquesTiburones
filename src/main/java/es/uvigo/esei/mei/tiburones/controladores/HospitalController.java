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

import es.uvigo.esei.mei.tiburones.entidades.Hospital;
import es.uvigo.esei.mei.tiburones.servicios.HospitalService;

@Controller
@RequestMapping("/hospitales")
public class HospitalController {
	@Autowired
	HospitalService hospitalService;

	@GetMapping
	public String prepararListarHospitales(Model modelo) {
		modelo.addAttribute("hospitales", hospitalService.buscarTodos());
		modelo.addAttribute("descripcionHospital", "");
		return "hospital/listado_hospitales";
	}

	@PostMapping
	public String actualizarListarHospital(@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String ciudad,
			@RequestParam(required = false) String pais, Model modelo) {
		List<Hospital> hospitales;
		if ((ciudad != null) && !ciudad.isEmpty()) {
			hospitales = hospitalService.buscarPorCiudad(ciudad);
		} else if ((nombre != null) && !nombre.isEmpty()) {
			hospitales = hospitalService.buscarPorNombre(nombre);
		} else if ((pais != null) && !pais.isEmpty()) {
			hospitales = hospitalService.buscarPorPais(pais);
		} else {
			hospitales = hospitalService.buscarTodos();
		}
		modelo.addAttribute("hospitales", hospitales);	
		return "hospital/listado_hospitales";
	}

	@GetMapping("nuevo")
	public ModelAndView prepararNuevoHospital() {
		Hospital hospital = new Hospital();

		ModelAndView result = new ModelAndView();
		result.addObject("hospital", hospital);
		result.addObject("esNuevo", true);
		result.setViewName("hospital/editar_hospital");
		return result;
	}

	@PostMapping("nuevo")
	public String crearHospital(@Valid @ModelAttribute Hospital hospital, BindingResult resultado) {
		if (!resultado.hasErrors()) {
			hospitalService.crear(hospital);
			return "redirect:/hospitales";
		} else {
			return null;
		}
	}

	@GetMapping("{id}")
	public String prepararEditarHospital(@PathVariable("id") Long id, Model modelo) {
		try {
			Hospital hospital = hospitalService.buscarPorId(id);
			modelo.addAttribute("hospital", hospital);
			modelo.addAttribute("esNuevo", false);			
			return "hospital/editar_hospital";
		} catch (EntityNotFoundException e) {
			modelo.addAttribute("error", "Hospital no encontrado");
			return "error";
		}
	}

	@PostMapping("{id}")
	public String actualizarHospital(@Valid @ModelAttribute Hospital hospital, BindingResult resultado) {
		if (!resultado.hasErrors()) {
			hospitalService.modificar(hospital);
			return "redirect:/hospitales";
		} else {
			return null;
		}
	}
	
	/**
	 * @PathVariable vincula el parametro a un segmento de la URI
	 */
	@GetMapping("{id}/eliminar")
	public String borrarHospital(@PathVariable("id") Long id, Model modelo) {
		Hospital hospital = hospitalService.buscarPorId(id);
		if (hospital != null) {
			hospitalService.eliminar(hospital);
			return "redirect:/hospitales";
		} else {
			modelo.addAttribute("mensajeError", "Hospital no encontrada");
			return "error";
		}
	}
}
