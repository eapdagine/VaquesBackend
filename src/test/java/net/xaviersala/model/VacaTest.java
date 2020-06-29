package net.xaviersala.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

public class VacaTest {
	@BeforeEach
	void setUp() throws Exception {
	}

	static Stream<Arguments> CreatePesWithLitres() {
		return Stream.of(Arguments.of(12.0, 5.5), Arguments.of(5.0, 4.0));
	}

	@ParameterizedTest
	@MethodSource("CreatePesWithLitres")
	void testQueRetornaCorrectamentLaLlet(double pes, double litres) {

		// ARRANGE
		Raça racaFalsa = Mockito.mock(Raça.class);
		Mockito.when(racaFalsa.getLitresPerKg()).thenReturn(litres);

		Vaca sut = new Vaca("nom", pes, racaFalsa);

		// ACT
		double resultat = sut.getLitres();

		// ASSERT
		assertEquals(pes * litres, resultat);
	}

}