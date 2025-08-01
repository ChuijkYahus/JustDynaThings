package com.devdyna.justdynathings;

import com.devdyna.justdynathings.compat.core;
import com.devdyna.justdynathings.config.*;
import com.devdyna.justdynathings.datamaps.zDataMaps;
import com.devdyna.justdynathings.registry.Material;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(Main.ID)
public class Main {
        public static final String ID = "justdynathings";

        // ! Dont use this , intend of utils.LogUtil !
        // public static final Logger LOG = LogUtils.getLogger();

        public Main(IEventBus bus, ModContainer chest) {

                // startup.register(chest); //TODO need to define what to do with this
                common.register(chest);

                Material.register(bus);

                bus.addListener(Capabilities::regCap);
                bus.addListener(zDataMaps::register);

                core.regCompat(bus);

        }

}
