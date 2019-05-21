package ui;

import javafx.scene.control.ListCell;

public class ListViewCell extends ListCell<WeatherTileData>
{
    @Override
    public void updateItem(WeatherTileData data, boolean empty)
    {
        super.updateItem(data, empty);

        // If data is null show an AddListItem tile
        // Otherwise load the location tile into a ListItem

        if(data != null)
        {
            ListItem listItem = new ListItem();
            listItem.setInfo(data.getLocation(), data.isCurrentLocation());
            setGraphic(listItem.getContainer());
        } else {
            AddListItem addListItem = new AddListItem();
            setGraphic(addListItem.getContainer());
        }
    }
}