/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vistas;

import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapMouseEvent;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.MouseEvent;
import com.teamdev.jxmaps.swing.MapView;

import javax.swing.*;
import java.awt.*;

/**
 *
 *
 * @author pedro
 */
public class CreacionMapa extends MapView {
    
    public CreacionMapa() {
        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                if (status == MapStatus.MAP_STATUS_OK) {
                    final Map map = getMap();
                    MapOptions options = new MapOptions();
                    MapTypeControlOptions controlOptions = new MapTypeControlOptions();
                    controlOptions.setPosition(ControlPosition.TOP_RIGHT);
                    options.setMapTypeControlOptions(controlOptions);
                    map.setOptions(options);
                    map.setCenter(new LatLng(13.794185, -88.89653));
                    map.setZoom(9.0);
                    Marker marker = new Marker(map);
                    //marker.setPosition(map.getCenter());
                    final InfoWindow infoWindow = new InfoWindow(map);
                    //infoWindow.setContent("Texto");
                    infoWindow.open(map, marker);
                    map.addEventListener("click", new MapMouseEvent() {
                        @Override
                        public void onEvent(MouseEvent mouseEvent) {
                            infoWindow.close();
                            final Marker marker = new Marker(map);
                            String latitudLongitud = mouseEvent.latLng().toString();
                            
                            if (JOptionPane.showConfirmDialog(null, latitudLongitud, "Esta seguro?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                String nombre = JOptionPane.showInputDialog("Ingrese el Nombre de la Ubicacion");
                                if (nombre.equals("")) {
                                    marker.remove();
                                }
                            } else {
                                marker.remove();
                            }
                            
                            marker.setPosition(mouseEvent.latLng());
                            
                            marker.addEventListener("click", new MapMouseEvent() {
                                @Override
                                public void onEvent(MouseEvent mouseEvent) {
                                    marker.remove();
                                }
                            });
                        }
                    });
                }
            }
        });
    }
    
    public static void main(String[] args) {
        final CreacionMapa sample = new CreacionMapa();
        
        JFrame frame = new JFrame("Marcadores");
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(sample, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
