package br.com.ciberian.servicos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CalculadoraServicoTest {
	static CalculadoraServico servico;

	@BeforeAll
	public static void setup() {
		servico = new CalculadoraServico();
	}

	@Test
	public void deveCalcularImc() throws Exception {
		// Ação
		double imc = servico.calcularImc(79.0, 1.76);

		// 25,50361570247934
		// Validação
		Assertions.assertEquals(25.5, imc, 0.01);
	}

	@Test
	public void deveCalcularImcComAlturaZerada() {
		// Ação e Validação
		Assertions.assertThrows(IllegalArgumentException.class, () -> servico.calcularImc(79, 0));
	}

	@Test
	public void deveCalcularImcComPesoZerado() {
		// Ação e Validação
		Assertions.assertThrows(IllegalArgumentException.class, () -> servico.calcularImc(0, 1.76));
	}

	@Test
	public void deveCalcularImcComValoresZerados() {
		// Ação e Validação
		Assertions.assertThrows(IllegalArgumentException.class, () -> servico.calcularImc(0, 0));
	}

	@Test
	public void deveClassificarAbaixoPeso() throws Exception {
		// Cenario
		double imc = servico.calcularImc(57, 1.76);

		// Ação
		String classificacao = servico.classificarImc(imc);

		// Validação
		Assertions.assertEquals("Abaixo do Peso", classificacao);
	}

	@Test
	public void deveClassificarPesoIdeal() throws Exception {
		// Cenario
		double imc = servico.calcularImc(65, 1.76);

		// Ação
		String classificacao = servico.classificarImc(imc);

		// Validação
		Assertions.assertEquals("Peso Ideal (Parabens)", classificacao);
	}

	@Test
	public void deveClassificarAcimaPeso() throws Exception {
		// Cenario
		double imc = servico.calcularImc(92.9, 1.76);

		// Ação
		String classificacao = servico.classificarImc(imc);

		// Validação
		Assertions.assertEquals("Levemente Acima do Peso", classificacao);
	}

	@Test
	public void naoDeveClassificarAcimaPeso() throws Exception {
		// Cenario
		double imc = servico.calcularImc(93, 1.76);

		// Ação
		String classificacao = servico.classificarImc(imc);

		// Validação
		Assertions.assertNotEquals("Acima do peso", classificacao);
	}

	@Test
	public void deveClassificarObesidade1() throws Exception {
		// Cenario
		double imc = servico.calcularImc(93, 1.76);

		// Ação
		String classificacao = servico.classificarImc(imc);

		// Validação
		Assertions.assertEquals("Obesidade Grau I", classificacao);
	}

	@Test
	public void deveClassificarObesidade2() throws Exception {
		// Cenario
		double imc = servico.calcularImc(110, 1.76);

		// Ação
		String classificacao = servico.classificarImc(imc);

		// Validação
		Assertions.assertEquals("Obesidade Grau II", classificacao);
	}

	@Test
	public void deveClassificarObesidadeSevera() throws Exception {
		// Cenario
		double imc = servico.calcularImc(130, 1.76);

		// Ação
		String classificacao = servico.classificarImc(imc);

		// Validação
		Assertions.assertEquals("Obesidade Grau III", classificacao);
	}

	@ParameterizedTest(name = "Altura {0}, Peso {1}, Situação {2}")
	@CsvFileSource(resources = "/imc.csv", delimiter = ';')
	public void deveClassificarImcs(double alturas, double pesos, String mensagens) throws Exception {
		// Cenario
		double imc = servico.calcularImc(pesos, alturas);

		// Ação
		String classificacao = servico.classificarImc(imc);

		// Validação
		Assertions.assertEquals(mensagens, classificacao);
	}
}