<?php

header('Content-Type: application/json; charset=utf-8');

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    header('Allow: POST');
    http_response_code(405);
    echo json_encode(['error' => 'Gunakan metode POST']);
    exit;
}

$data = json_decode(file_get_contents('php://input'), true);
$nama = is_string($data['nama'] ?? null) ? trim($data['nama']) : '';
$alamat = is_string($data['alamat'] ?? null) ? trim($data['alamat']) : '';
$telpon = is_string($data['telpon'] ?? null) ? trim($data['telpon']) : '';

if ($nama === '' || $alamat === '' || $telpon === '') {
    http_response_code(400);
    echo json_encode(['error' => 'Semua kolom wajib diisi']);
    exit;
}

mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

try {
    require __DIR__ . '/koneks.php';
    $conn->set_charset('utf8mb4');
    $statement = $conn->prepare('INSERT INTO teman (nama, alamat, telpon) VALUES (?, ?, ?)');
    $statement->bind_param('sss', $nama, $alamat, $telpon);
    $statement->execute();
    http_response_code(201);
    echo json_encode(['message' => 'Data berhasil ditambahkan']);
} catch (mysqli_sql_exception $exception) {
    error_log($exception->getMessage());
    http_response_code(500);
    echo json_encode(['error' => 'Data gagal disimpan']);
}
