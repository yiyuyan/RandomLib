package cn.ksmcbrigade.RL;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("rl")
@Mod.EventBusSubscriber
public class RandomLib {

    public RandomLib() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void RegisterCommands(RegisterCommandsEvent event){
        event.getDispatcher().register(Commands.literal("random").executes(arg -> {
            Entity entity = arg.getSource().getEntity();
            entity.sendMessage(Component.nullToEmpty("Command usage: /random <type(number|string|color|item|block|enchant|entity)> <length(This parameter is valid only when type is number or string and this parameter type is int.)>"),entity.getUUID());
            return 0;
        }).then(Commands.argument("type",StringArgumentType.string()).executes(arg -> {
            Entity entity = arg.getSource().getEntity();
            entity.sendMessage(Component.nullToEmpty("Command usage: /random <type(number|string|color|item|block|enchant|entity)> <length(This parameter is valid only when type is number or string and this parameter type is int.)>"),entity.getUUID());
            return 0;
        }).then(Commands.argument("length", IntegerArgumentType.integer()).executes(arg -> {
            Entity entity = arg.getSource().getEntity();
            entity.sendMessage(Component.nullToEmpty("Return: "+Libs.getRandom(StringArgumentType.getString(arg,"type"),IntegerArgumentType.getInteger(arg,"length"))),entity.getUUID());
            return 0;
        }))));

        event.getDispatcher().register(Commands.literal("aboutRandom").executes(arg -> {
            Entity entity = arg.getSource().getEntity();
            entity.sendMessage(Component.nullToEmpty("Command usage: /aboutRandom <type(number|string|color|item|block|enchant|entity)>"),entity.getUUID());
            return 0;
        }).then(Commands.argument("type",StringArgumentType.string()).executes(arg -> {
            Entity entity = arg.getSource().getEntity();
            entity.sendMessage(Component.nullToEmpty(Libs.aboutRandom(StringArgumentType.getString(arg,"type"))),entity.getUUID());
            return 0;
        })));
    }
}
