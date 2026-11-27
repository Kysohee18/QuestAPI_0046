<?php

header('Content-Type: application/json; charset=utf-8');
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

try {
    require __DIR__ . '/koneks.php';
    $conn->set_charset('utf8mb4');
    $result = $conn->query('SELECT id, nama, alamat, telpon FROM teman ORDER BY nama, id');
    $siswa = [];
    while ($row = $result->fetch_assoc()) {
        $siswa[] = [
            'id' => (int) $row['id'],
            'nama' => (string) $row['nama'],
            'alamat' => (string) $row['alamat'],
            'telpon' => (string) $row['telpon'],
        ];
    }
    echo json_encode($siswa, JSON_THROW_ON_ERROR);
} catch (mysqli_sql_exception | JsonException $exception) {
    error_log($exception->getMessage());
    http_response_code(500);
    echo json_encode(['error' => 'Data gagal dimuat']);
}
