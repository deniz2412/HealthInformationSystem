package com.his.util;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.Region;

import java.util.List;

public class JFoenixTableUtils<T extends RecursiveTreeObject<T>> {



    public void generateColumns(JFXTreeTableView<T> table, List<String> columnNames, List<String> propertyNames) {

        double tableWidth = table.getWidth();
        int columnCount = columnNames.size();
        double columnWidth = tableWidth / columnCount;

        for (int i = 0; i < columnNames.size(); i++) {
            String columnName = columnNames.get(i);
            String propertyName = propertyNames.get(i);

            JFXTreeTableColumn<T, String> column = new JFXTreeTableColumn<>(columnName);
            column.setCellValueFactory(new TreeItemPropertyValueFactory<>(propertyName));
            column.setPrefWidth(columnWidth);
            table.getColumns().add(column);
        }

        table.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
        table.setMinWidth(Region.USE_PREF_SIZE);
        table.setMaxWidth(Region.USE_PREF_SIZE);
    }

    public void populateTableData(JFXTreeTableView<T> table, List<T> data) {
        ObservableList<T> observableData = FXCollections.observableArrayList(data);
        RecursiveTreeItem<T> root = new RecursiveTreeItem<T>(observableData, RecursiveTreeObject::getChildren) {};
        table.setRoot(root);
        table.setShowRoot(false);
    }

}
