# WalkMate 21-class YOLO26n 768 candidate

- Source checkpoint: `best.pt` from training run `yolo26n_reviewed_768_e20_v1` (20 epochs, 768 px).
- Source SHA-256: `026e4d36f7cc62c302e0841dec1fbc85c7cabd29fe729fb82625477a4aa70319`.
- Architecture: YOLO26n detect, 21 WalkMate classes, end-to-end head.
- Android artifact: `apps/android/app/src/main/assets/models/walkmate_yolo26n_21cls_768_float32.tflite`.
- Android artifact SHA-256: `75d66fe4cafc7fa57bcfbc86cad0e005042aa926105774079b0f5a8190a9e04b` (10,086,244 bytes).
- Export toolchain: Ultralytics 8.4.48, TensorFlow 2.19.1, float32 input/output.
- Tensor contract: input `[1, 768, 768, 3]`, output `[1, 300, 6]` containing normalized `[x1, y1, x2, y2, confidence, classId]` rows.
- The large source checkpoint remains outside Git; this directory records only reproducible provenance.
