# Person-only auxiliary YOLO26n

- Source checkpoint: user-provided `yolo26n.pt` (COCO 80 classes, class `0` is `person`).
- Source SHA-256: `9b09cc8bf347f0fc8a5f7657480587f25db09b34bf33b0652110fb03a8ad4fef`.
- Android artifact: `apps/android/app/src/main/assets/models/person_yolo26n_coco_640_float32.tflite`.
- Android artifact SHA-256: `64e9694cf07e44330de8f5a300d442c49e5703d68bd9f5e2bf905b1d7a8ceab0` (10,229,913 bytes).
- Export toolchain: Ultralytics 8.4.48, TensorFlow 2.19.1, float32 input/output.
- Tensor contract: input `[1, 640, 640, 3]`, output `[1, 300, 6]` normalized `[x1, y1, x2, y2, confidence, classId]` rows.
- Android applies an allowlist containing only `person`, which is the on-device equivalent of Ultralytics inference with `classes=[0]`.
- The source checkpoint remains outside Git; this directory records reproducible provenance only.
