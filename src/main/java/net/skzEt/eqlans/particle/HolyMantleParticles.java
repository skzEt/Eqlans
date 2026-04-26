package net.skzEt.eqlans.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jspecify.annotations.Nullable;

public class HolyMantleParticles extends SingleQuadParticle {

    public HolyMantleParticles(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, TextureAtlasSprite sprite) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, sprite);

        this.friction = 0.6f;
        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = xSpeed;
        this.quadSize *= 0.75f;
        this.lifetime = 100 + this.random.nextInt(12);
        this.setSprite(sprite);
        this.gravity = 1.25f;
    }

    @Override
    protected Layer getLayer() {
        return Layer.OPAQUE;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double v, double v1, double v2, double v3, double v4, double v5, RandomSource randomSource) {
            return new HolyMantleParticles(clientLevel, v, v1, v2, v3, v4, v5, this.spriteSet.get(randomSource));
        }
    }
}
