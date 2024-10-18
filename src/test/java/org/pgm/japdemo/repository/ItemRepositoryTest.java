package org.pgm.japdemo.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.pgm.japdemo.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void saveItemTest(){
        Item item = Item.builder()
                .itemNn("공책")
                .price(5000)
                .itemDetail("알림장 노트")
                .stockNumber(10)
                .build();
        itemRepository.save(item);
    }
    @Test
    public void findAllTest(){
        List<Item> items = itemRepository.findAll();
        for(Item item : items){
            log.info(item.toString());
        }
    }
    @Test
    public void updateTest(){
        Item item=itemRepository.findById(1l).get();
        item.setItemNn("볼펜");
        item.setPrice(1000);
        item.setStockNumber(100);
        itemRepository.save(item);

        log.info(item.toString());
    }
    @Test
    public void deleteTest(){
        itemRepository.deleteById(1l);
    }
}
