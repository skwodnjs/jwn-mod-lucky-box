package net.jwn.mod.networking.packet;

import net.jwn.mod.block.LuckyBoxBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class LuckyBoxGetC2SPacket {
    BlockPos luckyBoxPos;

    public LuckyBoxGetC2SPacket(BlockPos pos) {
        this.luckyBoxPos = pos;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(luckyBoxPos);
    }

    public LuckyBoxGetC2SPacket(FriendlyByteBuf buf) {
        luckyBoxPos = buf.readBlockPos();
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();

            if (player.level().getBlockEntity(luckyBoxPos) instanceof LuckyBoxBlockEntity luckyBoxBlockEntity) {
                luckyBoxBlockEntity.pressButton();
            }
        });
        return true;
    }
}
