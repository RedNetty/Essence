package net.rednetty.essence.items.book;

import net.rednetty.essence.items.interact.InteractableItem;
import net.rednetty.essence.mechanics.player.eplayer.EssencePlayer;
import net.rednetty.essence.utils.string.HexColors;
import net.rednetty.essence.utils.string.StringUtil;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.Arrays;

/**
 * Created by Giovanni on 2/11/2021
 */
public class BookItem extends InteractableItem {

    public BookItem(BookItemData itemData) {
        super(itemData);
        if (itemData.getAuthor() != null && itemData.getAuthor().length() > 0)
            setCustomLore(Arrays.asList("", "&7A book written by " + HexColors.BROWN + itemData.getAuthor()));
    }

    @Override
    public void onItemUse(EssencePlayer user) {
        BookItemData data = (BookItemData) getItemData();
        String author = data.getAuthor();

        BookItemData itemData = (BookItemData) getItemData();
        if (itemData.getPages() == null || itemData.getPages().isEmpty()) {
            user.centeredMessage("&7&oThe contents of this book seem to have been shattered..");
            return;
        }

        ItemStack properBook = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) properBook.getItemMeta();
        bookMeta.setTitle("BookItem");
        bookMeta.setAuthor(itemData.getAuthor());
        for (BookPage bookPage : itemData.getPages())
            bookMeta.addPage(StringUtil.applyColors(bookPage.getRawText()));
        properBook.setItemMeta(bookMeta);
        user.getPlayer().openBook(properBook);
    }
}
