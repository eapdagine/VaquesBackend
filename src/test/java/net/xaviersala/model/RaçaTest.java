package net.xaviersala.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author xavier
 *
 */
public class RaçaTest {

	Raça sut;

	@BeforeEach
	public void setUp() throws Exception {

	}

	/*
	 * Comprova que es retorna el nom que hem posat
	 */
	@ParameterizedTest
	@ValueSource(strings = { "Holdstein", "X", "XXXXXXXXXX" })
	public void testQueElNomEsElQueLiHemDonat(String nom) {
		// ARRANGE
		sut = new Raça(nom, 5);

		// ACT
		String resultat = sut.getNom();

		// ASSERT
		assertEquals(nom, resultat, "El nom de la Raça no s'esta inicialitzant bé");
	}

	/*
	 * Comprova que si rep null retorna raça desconeguda
	 */
	@Test
	public void testQueSiNoLiDonemNomLaMarcaDesconeguda() {
		// ARRANGE
		sut = new Raça(null, 10);

		// ACT
		String resultat = sut.getNom();

		// ASSERT
		assertEquals(Raça.DESCONEGUDA, resultat);
	}

	@Test
	public void testQueSiElNomEsBuitRetornaRaçaDesconeguda() {
		// ARRANGE
		sut = new Raça("", 10);

		// ACT
		String resultat = sut.getNom();

		// ASSERT
		assertEquals(Raça.DESCONEGUDA, resultat);
	}

	@ParameterizedTest
	@ValueSource(strings = { "  ", "\t", "\n" })
	public void testQueElsNomsBuitsRetornenDesconeguda(String nom) {
		// ARRANGE
		sut = new Raça(nom, 10);

		// ACT
		String resultat = sut.getNom();

		// ASSERT
		assertEquals(Raça.DESCONEGUDA, resultat);
	}
	
	@ParameterizedTest
	@ValueSource(doubles = { 0.0, -1.0, -2 })
	public void testQueSiElValorDelsLitresPerKgEsNegatiuRetornaZero(double litres) {
		// ARRANGE
		sut = new Raça("qualsevol", litres);
		
		// ACT
		double resultat = sut.getLitresPerKg();
		
		// ASSERT
		assertEquals(0.0, resultat);
	}
	
	@ParameterizedTest
	@ValueSource(doubles = { 0.0, 1.0, 2, 3.5, Double.MAX_VALUE })
	public void testQueSiElValorDelsLitresPerKgEsPositiuElsPosaBe(double litres) {
		// ARRANGE
		sut = new Raça("qualsevol", litres);
		
		// ACT
		double resultat = sut.getLitresPerKg();
		
		// ASSERT
		assertEquals(litres, resultat);
	}

}

