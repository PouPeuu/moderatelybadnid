package com.poupeuu.moderatelybadnid.models;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.ibm.icu.text.Normalizer2;
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
import net.minecraft.world.entity.decoration.ArmorStand;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class SopoModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ModeratelyBadNid.MODID, "sopo"), "main"); // com.poupeuu.moderatelybadnid.models.sopo?
	private final ModelPart kokokeho;
	private final ModelPart head;
	private final ModelPart left_hand;
	private final ModelPart right_hand;
	private final ModelPart left_foot;
	private final ModelPart right_foot;

	public SopoModel(ModelPart root) {
		this.kokokeho = root.getChild("kokokeho");
		this.head = kokokeho.getChild("paa");
		ModelPart hands = kokokeho.getChild("kadet");
		left_hand = hands.getChild("vasenkasi");
		right_hand = hands.getChild("oikeakasi");
		ModelPart legs = kokokeho.getChild("jalat");
		left_foot = legs.getChild("left_foot");
		right_foot = legs.getChild("right_foot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition kokokeho = partdefinition.addOrReplaceChild("kokokeho", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

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
				.texOffs(28, 5).addBox(-1.0F, 15.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, 10.0F));

		PartDefinition paa = kokokeho.addOrReplaceChild("paa", CubeListBuilder.create().texOffs(35, 60).addBox(-2.0F, -6.0F, 6.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(12, 36).addBox(-1.0F, -10.0F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(32, 13).addBox(-2.0F, -9.0F, -4.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(22, 26).addBox(-4.0F, -8.0F, -5.0F, 8.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(12, 36).addBox(4.0F, -7.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -7.0F, -6.0F, 8.0F, 9.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(34, 45).addBox(-2.0F, -8.0F, -6.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(60, 40).addBox(-2.0F, -8.0F, 5.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 36).addBox(4.0F, -6.0F, -5.0F, 1.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(42, 37).addBox(5.0F, -2.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(24, 26).addBox(5.0F, -3.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 26).addBox(5.0F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 10).addBox(-3.0F, -5.0F, -7.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(22, 37).addBox(-2.0F, -4.0F, -8.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(58, 56).addBox(-2.0F, -6.0F, -7.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 7).addBox(-2.0F, -4.0F, 7.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(44, 48).addBox(-3.0F, -5.0F, 6.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(22, 37).addBox(-5.0F, -6.0F, -5.0F, 1.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, 0.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition oikeakorva = paa.addOrReplaceChild("oikeakorva", CubeListBuilder.create().texOffs(44, 55).addBox(-1.0F, -17.0F, -10.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(24, 21).addBox(-1.0F, -15.0F, -9.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -19.0F, -11.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(34, 37).addBox(-1.0F, -21.0F, -11.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(24, 23).addBox(-1.0F, -20.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition vasenkorva = paa.addOrReplaceChild("vasenkorva", CubeListBuilder.create().texOffs(48, 0).addBox(-1.0F, -21.0F, 5.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(54, 33).addBox(-1.0F, -19.0F, 6.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(56, 0).addBox(-1.0F, -17.0F, 7.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(4, 26).addBox(-1.0F, -15.0F, 8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(28, 26).addBox(-1.0F, -20.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition hanta = kokokeho.addOrReplaceChild("hanta", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition alahanta = hanta.addOrReplaceChild("alahanta", CubeListBuilder.create().texOffs(48, 17).addBox(-8.0F, -3.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(52, 56).addBox(-7.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ylahanta = hanta.addOrReplaceChild("ylahanta", CubeListBuilder.create().texOffs(0, 21).addBox(-9.0F, -6.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 36).addBox(-10.0F, -13.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition jalat = kokokeho.addOrReplaceChild("jalat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_foot = jalat.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(34, 22).addBox(4.0F, -1.0F, -5.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_foot = jalat.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(22, 43).addBox(4.0F, -1.0F, 2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition kadet = kokokeho.addOrReplaceChild("kadet", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, -9.0F));

		PartDefinition vasenkasi = kadet.addOrReplaceChild("vasenkasi", CubeListBuilder.create(), PartPose.offset(0.0F, -2.75F, 12.75F));

		PartDefinition cube_r1 = vasenkasi.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(44, 22).addBox(-1.0F, -6.0F, -5.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.75F, 7.25F, 0.5236F, 0.0F, 0.0F));

		PartDefinition oikeakasi = kadet.addOrReplaceChild("oikeakasi", CubeListBuilder.create(), PartPose.offset(0.0F, -2.75F, 5.25F));

		PartDefinition cube_r2 = oikeakasi.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(44, 22).addBox(-1.0F, 0.0066F, -1.9037F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingOffset, float ageInTicks, float netHeadYawDeg, float headPitchDeg) {
		float headPitch = headPitchDeg * ((float) Math.PI / 180f);
		float headYaw = netHeadYawDeg * ((float) Math.PI / 180f);

		this.head.yRot = headYaw;
		this.head.xRot = headPitch;
		this.head.zRot = 0;

		this.right_hand.zRot = (float) Math.cos(limbSwing);
		this.left_hand.zRot = (float) Math.cos(limbSwing + (float) Math.PI);
		this.right_foot.zRot = (float) Math.cos(limbSwing + (float) Math.PI);
		this.left_foot.zRot = (float) Math.cos(limbSwing);

		//1ModeratelyBadNid.LOGGER.debug("LimbSwing: " + limbSwing + " Amount: " + limbSwingOffset);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		kokokeho.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}