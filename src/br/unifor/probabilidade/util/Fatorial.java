package br.unifor.probabilidade.util;

import java.text.DecimalFormat;

public class Fatorial {
	private int calculoFatorial(int numero) {
		int fat = 1;
		while (numero >= 2) {
			fat *= numero;
			numero -= 1;
		}
		return fat;
	}

	public Double convertDouble(String entrada) {
		Double valor = Double.parseDouble(entrada.replace(',', '.'));
		return valor;
	}
	
	public Double calculoPx(int n, int k, Double p, Double q) {
		Double px = (double) (((calculoFatorial(n) / ((calculoFatorial(n - k)) * calculoFatorial(k))))
				* Math.pow(p, k) * (Math.pow(q, (n - k))));
		return px;
	}

	public float calculoPx2(int n, int k, float p, float q) {
		float px = (float) ((float) (calculoFatorial(n) / (calculoFatorial(k) * calculoFatorial(n
				- k)))
				* Math.pow(p, k) * Math.pow(q, (float) (n - k)));
		return px;
	}

	public Double casoAoMenos(int n, int k, double p, double q) {
		DecimalFormat df = new DecimalFormat("#.####");
		int k1 = k;
		double resultado = 0.0;

		for (int i = k1; i <= n; i++) {
			resultado += calculoPx(n, k1, p, q);
			System.out.println("k = " + k1 + " resultado: "
					+ df.format(resultado));
			k1++;
		}
		return resultado;
	}
	
	public Double casoNoMaximo(int n, int k, double p, double q){
		DecimalFormat df = new DecimalFormat("#.####");
		int k1 = k;
		double resultado = 0.0;

		for (int i = k1; i >= 0; i--) {
			resultado += calculoPx(n, k1, p, q);
			System.out.println("k = " + k1 + " resultado: "
					+ df.format(resultado));
			k1--;
		}
		return resultado;
	}
}
