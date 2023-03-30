package com.company.data_rest.service;

import com.company.data_rest.entity.Client;
import com.company.data_rest.entity.Currency;
import com.company.data_rest.entity.Output;
import com.company.data_rest.entity.Warehouse;
import com.company.data_rest.payload.OutputDto;
import com.company.data_rest.payload.Result;
import com.company.data_rest.repository.ClientRepository;
import com.company.data_rest.repository.CurrencyRepository;
import com.company.data_rest.repository.OutputRepository;
import com.company.data_rest.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public List<Output> get(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Output> outputPage = outputRepository.findAll(pageable);
        return outputPage.getContent();
    }

    public Output getById(Integer id) {
        return outputRepository.findById(id).orElse(null);
    }

    public Result create(OutputDto outputDto) {
        if (outputRepository.existsByFacture_number(outputDto.getFacture_number())) {
            return new Result("Bunday faktura raqamli chiqim mavjud", false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()) {
            return new Result("Bunday ombor mavjud emas", false);
        }
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (optionalClient.isEmpty()) {
            return new Result("Bunday mijoz topilmadi", false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()) {
            return new Result("Bunday valyuta topilmadi", false);
        }
        Output output = new Output(outputDto.getDate(), outputDto.getFacture_number(), outputDto.getCode(),
                optionalClient.get(), optionalWarehouse.get(), optionalCurrency.get());
        outputRepository.save(output);
        return new Result("Chiqim saqlandi", true);

    }

    public Result edit(Integer id, OutputDto outputDto) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty()) {
            return new Result("Bunday chiqim topilmadi", false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()) {
            return new Result("Bunday ombor mavjud emas", false);
        }
        if (outputRepository.existsByFacture_number(outputDto.getFacture_number())) {
            return new Result("Bunday faktura raqamli chiqim mavjud", false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()) {
            return new Result("Bunday valyuta topilmadi", false);
        }
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (optionalClient.isEmpty()) {
            return new Result("Bunday mijoz topilmadi", false);
        }
        Output output = optionalOutput.get();
        output.setClient(optionalClient.get());
        output.setDate(outputDto.getDate());
        output.setCode(outputDto.getCode());
        output.setCurrency(optionalCurrency.get());
        output.setWarehouse(optionalWarehouse.get());
        outputRepository.save(output);
        return new Result("Chiqim taxrirlandi", true);
    }

    public Result delete(Integer id) {
        try {
            outputRepository.deleteById(id);

            return new Result("Chiqim o'chiirldi", true);
        } catch (Exception e) {
            return new Result("Chiqim o'chirilmadi", false);
        }
    }
}
