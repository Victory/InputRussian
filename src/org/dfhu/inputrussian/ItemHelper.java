package org.dfhu.inputrussian;

import com.vaadin.data.Item;

public class ItemHelper {
    private final Item item;

    public ItemHelper(Item item) {
        this.item = item;
    }

    @SuppressWarnings("unchecked")
    public void add(String key, String value) {
        item.getItemProperty(key).setValue(value);
    }
}
