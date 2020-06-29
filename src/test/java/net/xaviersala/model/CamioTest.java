package net.xaviersala.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

class CamioTest {

	private static final int MAXPES = 10;

	@BeforeEach
	void setUp() throws Exception {
	}

	@ParameterizedTest
	@ValueSource(doubles = {5.0, 10.0, 0.0})
	void testQueTotVaBeQuanEntraUnaVacaEnUnCamioOnHiCap(double pes) {
		double llet = 8.0;
		// ARRANGE
		Vaca vacaFalsa = Mockito.mock(Vaca.class);
		Mockito.when(vacaFalsa.getPes()).thenReturn(pes);
		Mockito.when(vacaFalsa.getLitres()).thenReturn(llet);
				
		Camio sut = new Camio(10);
		
		// ACT
		var resultat = sut.EntraVaca(vacaFalsa);
		
		// ASSERT
		assertTrue(resultat, "La vaca ha de poder entrar");
		assertEquals(pes, sut.getPesActual());
		assertEquals(llet, sut.getLitres());
	}
	
	@ParameterizedTest
	@ValueSource(doubles = {10.0001, 11.0, 50.0})
	void testQueTotNOEntraUnaVacaEnUnCamioSiPesaMassa(double pes) {
		double llet = 8.0;
		// ARRANGE
		Vaca vacaFalsa = Mockito.mock(Vaca.class);
		Mockito.when(vacaFalsa.getPes()).thenReturn(pes);
		Mockito.when(vacaFalsa.getLitres()).thenReturn(llet);
				
		Camio sut = new Camio(10);
		
		// ACT
		var resultat = sut.EntraVaca(vacaFalsa);
		
		// ASSERT
		assertFalse(resultat, "La vaca NO ha de poder entrar");
		assertEquals(0, sut.getPesActual());
		assertEquals(0, sut.getLitres());
	}
	
	

	static Stream<Arguments> CreaVaquesQueHanDePoderEntrar(){
		
		Vaca[] vaques = new Vaca[6];
		
		for(int i=1; i<=5; i++) { 
			vaques[i] = Mockito.mock(Vaca.class);
			Mockito.when(vaques[i].getPes()).thenReturn((double)i);
			Mockito.when(vaques[i].getLitres()).thenReturn((double)i*2);
		}
				
		return Stream.of(
			Arguments.of(Arrays.asList(new Vaca[]{ vaques[5], vaques[5]}), 10.0),
			Arguments.of(Arrays.asList(new Vaca[] { vaques[2], vaques[3], vaques[5]}), 10.0),
			Arguments.of(Arrays.asList(new Vaca[] {vaques[1], vaques[2], vaques[5], vaques[1]}), 9.0)
		);
	}
		
	@ParameterizedTest
	@MethodSource("CreaVaquesQueHanDePoderEntrar")
	void testQueLesVaquesEntrenAlCamio(List<Vaca> vaques, double expectedPes) {

		// ARRANGE
		
				
		Camio sut = new Camio(10);
		
		// ACT
		for (Vaca vaca : vaques) {
			var resultat = sut.EntraVaca(vaca);
			assertTrue(resultat, "La vaca ha de poder entrar");
		}
		
		// ASSERT
		assertEquals(expectedPes, sut.getPesActual());
		assertEquals(expectedPes * 2, sut.getLitres());   // llet el doble del pes.
		assertEquals(vaques.size(), sut.getVaques().size(), "Han d'haver entrat totes les vaques");
		assertEquals(vaques, sut.getVaques());
	}
	
	
	static Stream<Arguments> CreaVaquesQueNoPodenEntrarTotes(){
		int size = MAXPES + 2;
		Vaca[] vaques = new Vaca[size];
		
		for(int i=0; i < size; i++) { 
			vaques[i] = Mockito.mock(Vaca.class);
			Mockito.when(vaques[i].getPes()).thenReturn((double)i);
			Mockito.when(vaques[i].getLitres()).thenReturn((double)i*2);
		}
				
		return Stream.of(
			Arguments.of(Arrays.asList(new Vaca[]{ vaques[5], vaques[5], vaques[1]}), 10.0),
			Arguments.of(Arrays.asList(new Vaca[]{ vaques[5], vaques[5], vaques[1], vaques[1]}), 10.0),
			Arguments.of(Arrays.asList(new Vaca[]{ vaques[1], vaques[3], vaques[5], vaques[2]}), 9.0),
			Arguments.of(Arrays.asList(new Vaca[]{vaques[5], vaques[2], vaques[5], vaques[1]}), 8.0),
			Arguments.of(Arrays.asList(new Vaca[]{vaques[size-1]}), 0.0),
			Arguments.of(Arrays.asList(new Vaca[]{vaques[size-1], vaques[1], vaques[size-2]}), 1.0)
		);
	}
	
	@ParameterizedTest
	@MethodSource("CreaVaquesQueNoPodenEntrarTotes")
	void testQueNoHiCabenMesVaquesQueElPesMaxim(List<Vaca> vaques, double expectedPes) {

		// ARRANGE
						
		Camio sut = new Camio(MAXPES);
		double pes = 0;
		
		// ACT
		for (Vaca vaca : vaques) {
			var expected = pes + vaca.getPes() <= MAXPES;
			
			var resultat = sut.EntraVaca(vaca);
			if (resultat) pes += vaca.getPes();
			assertTrue(resultat == expected, "EntraVaca no retorna bé el resultat");
		}
		
		// ASSERT
		assertEquals(expectedPes, sut.getPesActual());
		assertEquals(expectedPes * 2, sut.getLitres());		
	}

}
