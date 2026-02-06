package br.com.ciberian.servicos;

public class CalculadoraServico {
	public double calcularImc(double peso, double altura) throws IllegalArgumentException {
		if (peso <= 0 || altura <= 0)
			throw new IllegalArgumentException("Os valores de altura e peso devem ser maiores que zero.");

		return peso / Math.pow(altura, 2);
	}

	public String classificarImc(double imc) {
		if (imc < 18.5)
			return "Abaixo do Peso";

		if (imc < 25)
			return "Peso Ideal (Parabens)";

		if (imc < 30)
			return "Levemente Acima do Peso";

		if (imc < 35)
			return "Obesidade Grau I";

		if (imc < 40)
			return "Obesidade Grau II";

		return "Obesidade Grau III";
	}
}