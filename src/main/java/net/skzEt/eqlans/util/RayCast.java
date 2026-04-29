package net.skzEt.eqlans.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;

import java.util.List;

public class RayCast {
    public static BlockPos rayBlock(Player player, Level level, int maxDistance) {
        Vec3 eyePos = player.getEyePosition(1);
        Vec3 viewPos = player.getLookAngle();
        Vec3 endPos = eyePos.add(viewPos.x * maxDistance, viewPos.y * maxDistance,
                viewPos.z * maxDistance);

        ClipContext context = new ClipContext(eyePos, endPos, ClipContext.Block.OUTLINE,
                ClipContext.Fluid.NONE, player);

        BlockHitResult result = level.clip(context);

        if (result.getType() == HitResult.Type.BLOCK) {
            return result.getBlockPos();
        }

        return null;
    }

    public static Entity rayEntity(Player player, Level level, int maxDistance) {
        Vec3 eyePos = player.getEyePosition(1);
        Vec3 viewPos = player.getLookAngle();
        Vec3 endPos = eyePos.add(viewPos.x * maxDistance, viewPos.y * maxDistance,
                viewPos.z * maxDistance);

        double closestDistance = Double.MAX_VALUE;

        AABB area = player.getBoundingBox().expandTowards(viewPos.x * maxDistance, viewPos.y * maxDistance,
                viewPos.z * maxDistance).inflate(1, 1, 1);
        List<Entity> entitiesList = level.getEntities(player, area, e -> e != player);

        for (Entity entity : entitiesList) {
            AABB bounds = entity.getBoundingBox().inflate(entity.getPickRadius());
            Vec3 intersection = bounds.clip(eyePos, endPos).orElse(null);

            if (intersection != null) {
                double distance = eyePos.distanceToSqr(intersection);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    EntityHitResult result = new EntityHitResult(entity, intersection);

                    if (result.getType() == HitResult.Type.ENTITY) {
                        return result.getEntity();
                    }
                }
            }
        }
        return null;
    }
}
