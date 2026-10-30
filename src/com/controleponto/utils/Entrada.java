package com.controleponto.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Leitura de dados do console com validação.
 * Repete a pergunta até o usuário digitar um valor válido,
 * para que um erro de digitação não derrube o fluxo do menu.
 */
public class Entrada {

	public static final DateTimeFormatter FORMATO_DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	private final Scanner sc;

	public Entrada(Scanner sc) {
		this.sc = sc;
	}

	public String lerTexto(String mensagem) {
		while (true) {
			System.out.print(mensagem);
			String valor = sc.nextLine().trim();
			if (!valor.isEmpty()) {
				return valor;
			}
			System.out.println("Este campo nao pode ficar vazio.");
		}
	}

	public int lerInt(String mensagem) {
		while (true) {
			System.out.print(mensagem);
			try {
				return Integer.parseInt(sc.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("Digite um numero inteiro valido.");
			}
		}
	}

	public double lerDouble(String mensagem) {
		while (true) {
			System.out.print(mensagem);
			try {
				double valor = Double.parseDouble(sc.nextLine().trim().replace(",", "."));
				if (valor < 0) {
					System.out.println("O valor nao pode ser negativo.");
					continue;
				}
				return valor;
			} catch (NumberFormatException e) {
				System.out.println("Digite um numero valido (ex.: 2500,50).");
			}
		}
	}

	public LocalDateTime lerDataHora(String mensagem) {
		while (true) {
			System.out.print(mensagem);
			try {
				return LocalDateTime.parse(sc.nextLine().trim(), FORMATO_DATA_HORA);
			} catch (DateTimeParseException e) {
				System.out.println("Formato invalido. Use dd/MM/aaaa HH:mm (ex.: 30/10/2026 08:00).");
			}
		}
	}

	// ---- Variações "Enter mantém o valor atual" (usadas na atualização) ----

	public String lerTextoOuManter(String mensagem, String atual) {
		System.out.print(mensagem + " [" + atual + "]: ");
		String valor = sc.nextLine().trim();
		return valor.isEmpty() ? atual : valor;
	}

	public double lerDoubleOuManter(String mensagem, double atual) {
		while (true) {
			System.out.print(mensagem + " [" + String.format("%.2f", atual) + "]: ");
			String valor = sc.nextLine().trim();
			if (valor.isEmpty()) {
				return atual;
			}
			try {
				double novo = Double.parseDouble(valor.replace(",", "."));
				if (novo < 0) {
					System.out.println("O valor nao pode ser negativo.");
					continue;
				}
				return novo;
			} catch (NumberFormatException e) {
				System.out.println("Digite um numero valido (ex.: 2500,50) ou Enter para manter.");
			}
		}
	}

	public int lerIntOuManter(String mensagem, int atual) {
		while (true) {
			System.out.print(mensagem + " [" + atual + "]: ");
			String valor = sc.nextLine().trim();
			if (valor.isEmpty()) {
				return atual;
			}
			try {
				return Integer.parseInt(valor);
			} catch (NumberFormatException e) {
				System.out.println("Digite um numero inteiro valido ou Enter para manter.");
			}
		}
	}

	public LocalDateTime lerDataHoraOuManter(String mensagem, LocalDateTime atual) {
		while (true) {
			System.out.print(mensagem + " [" + atual.format(FORMATO_DATA_HORA) + "]: ");
			String valor = sc.nextLine().trim();
			if (valor.isEmpty()) {
				return atual;
			}
			try {
				return LocalDateTime.parse(valor, FORMATO_DATA_HORA);
			} catch (DateTimeParseException e) {
				System.out.println("Formato invalido. Use dd/MM/aaaa HH:mm ou Enter para manter.");
			}
		}
	}

	/** Enter = data/hora atual (sem segundos). */
	public LocalDateTime lerDataHoraOuAgora(String mensagem) {
		while (true) {
			System.out.print(mensagem);
			String valor = sc.nextLine().trim();
			if (valor.isEmpty()) {
				return LocalDateTime.now().withSecond(0).withNano(0);
			}
			try {
				return LocalDateTime.parse(valor, FORMATO_DATA_HORA);
			} catch (DateTimeParseException e) {
				System.out.println("Formato invalido. Use dd/MM/aaaa HH:mm ou Enter para agora.");
			}
		}
	}

	/** Pergunta s/n até receber uma resposta válida. */
	public boolean confirmar(String mensagem) {
		while (true) {
			System.out.print(mensagem + " (s/n): ");
			String resposta = sc.nextLine().trim();
			if (resposta.equalsIgnoreCase("s")) {
				return true;
			}
			if (resposta.equalsIgnoreCase("n")) {
				return false;
			}
			System.out.println("Responda com 's' ou 'n'.");
		}
	}
}
