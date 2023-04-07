package com.company.data_rest.service;

import com.company.data_rest.entity.Currency;
import com.company.data_rest.entity.Input;
import com.company.data_rest.entity.Supplier;
import com.company.data_rest.entity.Warehouse;
import com.company.data_rest.payload.InputDto;
import com.company.data_rest.payload.Result;
import com.company.data_rest.repository.CurrencyRepository;
import com.company.data_rest.repository.InputsRepository;
import com.company.data_rest.repository.SupplierRepository;
import com.company.data_rest.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InputsService {
    @Autowired
    InputsRepository inputRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public List<Input> get(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Input> inputPage = inputRepository.findAll(pageable);
        return inputPage.getContent();
    }

    public Input getById(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.orElse(null);
    }

    public Result create(InputDto inputDto) {
        if (inputRepository.existsByFacture_number(inputDto.getFacture_number())) {
            return new Result("Bunday factura raqamli kirim mavjud", false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()) {
            return new Result("Bunday ombor mavjud emas", false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()) {
            return new Result("Bunday valyuta mavjud emas", false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (optionalSupplier.isEmpty()) {
            return new Result("Bunday ta'minotchi mavjud emas", false);
        }
        Input input = new Input(inputDto.getFacture_number(), inputDto.getCode(), optionalSupplier.get(),
                optionalWarehouse.get(), optionalCurrency.get());
        inputRepository.save(input);
        return new Result("Kirim saqlandi", true);
    }

    public Result edit(Integer id, InputDto inputDto) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isEmpty()) {
            return new Result("Bunday kirim topilmadi", false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()) {
            return new Result("Bunday ombor mavjud emas", false);
        }
        if (inputRepository.existsByFacture_number(inputDto.getFacture_number())) {
            return new Result("Bunday factura raqamli kirim mavjud", false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (optionalSupplier.isEmpty()) {
            return new Result("Bunday ta'minotchi mavjud emas", false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()) {
            return new Result("Bunday valyuta mavjud emas", false);
        }
        Input input = optionalInput.get();
        input.setCode(inputDto.getCode());
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        input.setWarehouse(optionalWarehouse.get());
        input.setFacture_number(inputDto.getFacture_number());
        inputRepository.save(input);
        return new Result("Kirim taxrirlandi", true);

    }

    public Result delete(Integer id) {
        if (inputRepository.findById(id).isEmpty()) {
            return new Result("Bunday kirim topilmadi", false);
        }
        inputRepository.deleteById(id);
        return new Result("Kirim o'chirildi", true);
    }
}
