package com.tenis.camisas.service;

import com.tenis.camisas.entity.Venda;
import com.tenis.camisas.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final VendaRepository vendaRepository;

    public ByteArrayInputStream relatorioVendasExcel() throws IOException {
        List<Venda> vendas = vendaRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Vendas");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("VendaId");
        header.createCell(1).setCellValue("Cliente");
        header.createCell(2).setCellValue("Vendedor");
        header.createCell(3).setCellValue("Data");
        header.createCell(4).setCellValue("ValorTotal");

        int rowIdx = 1;
        for (Venda v : vendas) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(v.getId());
            row.createCell(1).setCellValue(v.getCliente().getNome());
            row.createCell(2).setCellValue(v.getVendedor().getNome());
            row.createCell(3).setCellValue(v.getDataVenda().toString());
            row.createCell(4).setCellValue(v.getValorTotal());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
