package cc.unilock.breakeventtest;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.BlockEvent;
import io.github.fabricators_of_create.porting_lib.event.common.BlockEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.text.Text;

public class BreakEventTest implements ModInitializer {
	@Override
	public void onInitialize() {
		PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, entity) -> {
			player.sendMessage(Text.literal("(FabricAPI) PlayerBlockBreakEvents.BEFORE"));
			return true;
		});
		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
			player.sendMessage(Text.literal("(FabricAPI) PlayerBlockBreakEvents.AFTER"));
		});
		BlockEvents.BreakEvent.BLOCK_BREAK.register(event -> {
			event.getPlayer().sendMessage(Text.literal("(PortingLib) BlockEvents.BreakEvent.BLOCK_BREAK"));
		});
		BlockEvent.BREAK.register((level, pos, state, player, xp) -> {
			player.sendMessage(Text.literal("(Architectury) BlockEvent.BREAK"));
            return EventResult.pass();
        });
	}
}
