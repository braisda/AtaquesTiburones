package es.uvigo.esei.mei.tiburones.controladores.conversores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import es.uvigo.esei.mei.tiburones.entidades.Familia;
import es.uvigo.esei.mei.tiburones.servicios.ArticuloService;

@Component
public class ConversorFamilia implements Converter<String, Familia> {

	@Autowired
	private ArticuloService articuloService;

	@Override
	public Familia convert(String arg) {
		Long id = Long.parseLong(arg);
		return articuloService.buscarFamiliaPorId(id);
	}

}
