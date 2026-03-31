package com.ferry.food.domain.valueobjects;

import com.ferry.food.domain.exceptions.ValidationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
public class Taxa {
    private final BigDecimal valor;

    public Taxa(BigDecimal valor) {
        if (valor == null) {
            throw new ValidationException("Valor da taxa não pode ser nulo");
        }
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("Valor da taxa não pode ser negativo");
        }
        this.valor = valor;
    }

    public Taxa somar(Taxa outra) {
        if (outra == null) {
            return this;
        }
        return new Taxa(this.valor.add(outra.valor));
    }

    public Taxa subtrair(Taxa outra) {
        if (outra == null) {
            return this;
        }
        BigDecimal resultado = this.valor.subtract(outra.valor);
        if (resultado.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("Resultado da subtração não pode ser negativo");
        }
        return new Taxa(resultado);
    }

    public Taxa multiplicar(BigDecimal multiplicador) {
        if (multiplicador == null) {
            throw new ValidationException("Multiplicador não pode ser nulo");
        }
        return new Taxa(this.valor.multiply(multiplicador));
    }

    public boolean ehMaiorOuIgualA(Taxa outra) {
        if (outra == null) {
            return true;
        }
        return this.valor.compareTo(outra.valor) >= 0;
    }

    public boolean ehMenorOuIgualA(Taxa outra) {
        if (outra == null) {
            return false;
        }
        return this.valor.compareTo(outra.valor) <= 0;
    }

    public boolean ehIgualA(Taxa outra) {
        if (outra == null) {
            return false;
        }
        return this.valor.compareTo(outra.valor) == 0;
    }
}
