package com.epam.file;

import com.epam.container.TransportList;
import com.epam.transport.Automobile;
import com.epam.transport.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoodsSerializeTest {
    private final String fileDir = "D:\\Testic\\tests\\";
    private TransportList<Automobile> automobiles;
    private GoodsSerialize<Automobile> goodsSerialize;

    @BeforeEach
    void init() {
        automobiles = new TransportList<>();
        automobiles.add(new Automobile(200, 4, VehicleType.LAND, "Mazeratti", 20));
        automobiles.add(new Automobile(250, 4, VehicleType.LAND, "Lexus", 40));
    }


    @Test
    void read_ShouldReadSuccessfully_FileExists() {
        goodsSerialize = new GoodsSerialize<>(fileDir + "DefaultRead");
        TransportList<Automobile> transportList = goodsSerialize.read();
        assert (transportList != null);
    }

    @Test
    void save_ShouldCreateNewFile() {
        goodsSerialize = new GoodsSerialize<>(fileDir + "DefaultSave");
        goodsSerialize.save(automobiles);
    }

    @Test
    @DisplayName("save new file and do it 100 times to check the size")
    void save_ShouldCreateNewFile100() {
        goodsSerialize = new GoodsSerialize<>(fileDir + "Save x100");
        goodsSerialize.save(automobiles, 100);
    }

    @Test
    @DisplayName("save new file in GZip and check the size")
    void saveGzip_ShouldCreatNewFile() {
        goodsSerialize = new GoodsSerialize<>(fileDir + "GzipSave");
        goodsSerialize.saveGzip(automobiles);
    }
}