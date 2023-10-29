package com.poupeuu.moderatelybadnid.models;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class SopoModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ModeratelyBadNid.MODID, "sopo"), "main"); // com.poupeuu.moderatelybadnid.models.sopo?
	private final ModelPart kokokeho;

	public SopoModel(ModelPart root) {
		this.kokokeho = root.getChild("kokokeho");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition kokokeho = partdefinition.addOrReplaceChild("kokokeho", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 10.0F));

		PartDefinition maha = kokokeho.addOrReplaceChild("maha", CubeListBuilder.create().texOffs(44, 22).addBox(4.0F, 14.0F, -14.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(28, 0).addBox(-3.0F, 9.0F, -14.0F, 6.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(52, 49).addBox(3.0F, 10.0F, -13.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 21).addBox(-4.0F, 11.0F, -14.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(30, 54).addBox(-3.0F, 13.0F, -6.0F, 6.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(44, 37).addBox(-5.0F, 14.0F, -14.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(54, 17).addBox(-5.0F, 17.0F, -13.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(22, 54).addBox(-5.0F, 13.0F, -13.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 53).addBox(4.0F, 13.0F, -13.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(8, 53).addBox(4.0F, 17.0F, -13.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(7, 0).addBox(3.0F, 15.0F, -6.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(44, 33).addBox(-2.0F, 16.0F, -5.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(54, 40).addBox(-5.0F, 12.0F, -12.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(14, 53).addBox(-4.0F, 10.0F, -13.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(24, 21).addBox(-4.0F, 9.0F, -12.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(38, 10).addBox(-2.0F, 9.0F, -6.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 10).addBox(-2.0F, 9.0F, -15.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 60).addBox(-1.0F, 10.0F, -6.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(45, 60).addBox(-2.0F, 12.0F, -6.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 43).addBox(-2.0F, 16.0F, -16.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 22).addBox(-1.0F, 15.0F, -16.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(54, 24).addBox(-3.0F, 13.0F, -15.0F, 6.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.0F, 15.0F, -15.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 60).addBox(-1.0F, 10.0F, -15.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(60, 42).addBox(-2.0F, 12.0F, -15.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 5).addBox(-1.0F, 15.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition paa = kokokeho.addOrReplaceChild("paa", CubeListBuilder.create().texOffs(35, 60).addBox(-2.0F, -17.0F, 6.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 36).addBox(-1.0F, -21.0F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(32, 13).addBox(-2.0F, -20.0F, -4.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(22, 26).addBox(-4.0F, -19.0F, -5.0F, 8.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(12, 36).addBox(4.0F, -18.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-4.0F, -18.0F, -6.0F, 8.0F, 9.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(34, 45).addBox(-2.0F, -19.0F, -6.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(60, 40).addBox(-2.0F, -19.0F, 5.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 36).addBox(4.0F, -17.0F, -5.0F, 1.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(42, 37).addBox(5.0F, -13.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(24, 26).addBox(5.0F, -14.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 26).addBox(5.0F, -11.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(48, 10).addBox(-3.0F, -16.0F, -7.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 37).addBox(-2.0F, -15.0F, -8.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(58, 56).addBox(-2.0F, -17.0F, -7.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 7).addBox(-2.0F, -15.0F, 7.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(44, 48).addBox(-3.0F, -16.0F, 6.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 37).addBox(-5.0F, -17.0F, -5.0F, 1.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, -10.0F));

		PartDefinition oikeakorva = paa.addOrReplaceChild("oikeakorva", CubeListBuilder.create().texOffs(44, 55).addBox(-1.0F, -17.0F, -10.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(24, 21).addBox(-1.0F, -15.0F, -9.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.0F, -19.0F, -11.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(34, 37).addBox(-1.0F, -21.0F, -11.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(24, 23).addBox(-1.0F, -20.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition vasenkorva = paa.addOrReplaceChild("vasenkorva", CubeListBuilder.create().texOffs(48, 0).addBox(-1.0F, -21.0F, 5.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(54, 33).addBox(-1.0F, -19.0F, 6.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(56, 0).addBox(-1.0F, -17.0F, 7.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(4, 26).addBox(-1.0F, -15.0F, 8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 26).addBox(-1.0F, -20.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hanta = kokokeho.addOrReplaceChild("hanta", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, -10.0F));

		PartDefinition alahanta = hanta.addOrReplaceChild("alahanta", CubeListBuilder.create().texOffs(48, 17).addBox(-8.0F, -3.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(52, 56).addBox(-7.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ylahanta = hanta.addOrReplaceChild("ylahanta", CubeListBuilder.create().texOffs(0, 21).addBox(-9.0F, -6.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 36).addBox(-10.0F, -13.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition jalat = kokokeho.addOrReplaceChild("jalat", CubeListBuilder.create().texOffs(22, 43).addBox(4.0F, -1.0F, 2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(34, 22).addBox(4.0F, -1.0F, -5.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, -10.0F));

		PartDefinition kadet = kokokeho.addOrReplaceChild("kadet", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, -19.0F));

		PartDefinition vasenkasi = kadet.addOrReplaceChild("vasenkasi", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 20.0F));

		PartDefinition cube_r1 = vasenkasi.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(44, 22).addBox(-1.0F, -6.0F, -5.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition bone2 = kadet.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r2 = bone2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(44, 22).addBox(-1.0F, -6.0F, 3.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.5236F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		kokokeho.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}